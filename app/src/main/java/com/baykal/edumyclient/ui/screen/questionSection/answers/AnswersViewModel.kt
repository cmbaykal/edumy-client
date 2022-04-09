package com.baykal.edumyclient.ui.screen.questionSection.answers

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.answer.ClassAnswersUseCase
import com.baykal.edumyclient.data.domain.answer.DownVoteAnswerUseCase
import com.baykal.edumyclient.data.domain.answer.UpVoteAnswerUseCase
import com.baykal.edumyclient.data.domain.answer.UserAnswersUseCase
import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val session: EdumySession,
    private val userAnswersUseCase: UserAnswersUseCase,
    private val classAnswersUseCase: ClassAnswersUseCase,
    private val upVoteAnswerUseCase: UpVoteAnswerUseCase,
    private val downVoteAnswerUseCase: DownVoteAnswerUseCase
) : BaseViewModel() {

    private var userId: String? = null
    private var classId: String? = null

    private val _uiState = MutableStateFlow(AnswersState())
    val uiState = _uiState.asStateFlow()

    fun setImageDialog(dialogState: Boolean) {
        _uiState.update { it.copy(imageDialogState = dialogState) }
    }

    fun setImageUri(uri: String) {
        _uiState.update { it.copy(imageUri = uri, imageDialogState = true) }
    }

    fun fetchData() {
        args?.getString(QuestionsRoute.USER_ID)?.let {
            userId = it
        } ?: args?.getString(QuestionsRoute.CLASS_ID)?.let {
            classId = it
        }
        session.withUserId { userId ->
            _uiState.update { it.copy(userId = userId) }
        }
        getAnswers()
    }

    fun getAnswers(loading: Boolean = true) {
        userId?.let { id ->
            userAnswersUseCase.observe(id).collectData(loading) { response ->
                response?.let { list ->
                    _uiState.update { it.copy(answers = list) }
                }
            }
        } ?: classId?.let { id ->
            classAnswersUseCase.observe(id).collectData(loading) { response ->
                response?.let { list ->
                    _uiState.update { it.copy(answers = list) }
                }
            }
        }
    }

    fun upVoteAnswer(answer: Answer) {
        session.withUserId { userId ->
            answer.id?.let { answerId ->
                upVoteAnswerUseCase.observe(
                    UpVoteAnswerUseCase.Params(answerId, userId)
                ).collectData(false) {
                    getAnswers(false)
                }
            }
        }
    }

    fun downVoteAnswer(answer: Answer) {
        session.withUserId { userId ->
            answer.id?.let { answerId ->
                downVoteAnswerUseCase.observe(
                    DownVoteAnswerUseCase.Params(answerId, userId)
                ).collectData(false) {
                    getAnswers(false)
                }
            }
        }
    }
}