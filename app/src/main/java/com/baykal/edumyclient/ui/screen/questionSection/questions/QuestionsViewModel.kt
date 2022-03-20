package com.baykal.edumyclient.ui.screen.questionSection.questions

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.question.ClassQuestionsUseCase
import com.baykal.edumyclient.data.domain.question.QuestionsUseCase
import com.baykal.edumyclient.data.domain.question.UserQuestionsUseCase
import com.baykal.edumyclient.data.model.question.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val session: EdumySession,
    private val userQuestionsUseCase: UserQuestionsUseCase,
    private val classQuestionsUseCase: ClassQuestionsUseCase,
    private val questionsUseCase: QuestionsUseCase
) : BaseViewModel() {

    private var questionsPage = 0
    private val questionsLimit = 20

    private var userId: String? = null
    private var classId: String? = null

    private val questionList: MutableList<Question> = mutableListOf()
    private val _uiState = MutableStateFlow(QuestionsState())
    val uiState = _uiState.asStateFlow()

    fun fetchData() {
        args?.getString(QuestionsRoute.USER_ID)?.let {
            userId = it
        } ?: args?.getString(QuestionsRoute.CLASS_ID)?.let {
            classId = it
        } ?: run {
            session.withUser { user ->
                _uiState.update { it.copy(userRole = user.role) }
            }
        }
        getQuestions()
    }

    fun fetchQuestions() {
        _uiState.update { it.copy(isMoreData = true) }
        questionsPage = 0
        questionList.clear()
        getQuestions()
    }

    fun getQuestions() {
        userId?.let { id ->
            userQuestionsUseCase.observe(
                UserQuestionsUseCase.Params(
                    id,
                    questionsPage,
                    questionsLimit
                )
            ).collectData {
                setQuestions(it)
            }
        } ?: classId?.let { id ->
            classQuestionsUseCase.observe(
                ClassQuestionsUseCase.Params(
                    id,
                    questionsPage,
                    questionsLimit
                )
            ).collectData {
                setQuestions(it)
            }
        } ?: run {
            questionsUseCase.observe(
                QuestionsUseCase.Params(
                    questionsPage,
                    questionsLimit
                )
            ).collectData {
                setQuestions(it)
            }
        }
    }

    private fun setQuestions(list: MutableList<Question>?) {
        if (list != null && list.isNotEmpty()) {
            questionsPage += 1
            questionList.addAll(list)
            _uiState.update { it.copy(questions = questionList.toMutableList()) }
            filterQuestions(uiState.value.lesson)
        } else {
            _uiState.update { it.copy(isMoreData = false) }
        }
    }

    fun filterQuestions(lesson: String) {
        _uiState.update { it.copy(lesson = lesson) }
        val list = if (lesson == "All") {
            questionList
        } else {
            questionList.filter {
                it.lesson == lesson
            }.toMutableList()
        }
        _uiState.update { it.copy(questions = list) }
    }
}