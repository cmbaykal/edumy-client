package com.baykal.edumyclient.ui.screen.questionSection.questionDetail

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.answer.DownVoteAnswerUseCase
import com.baykal.edumyclient.data.domain.answer.UpVoteAnswerUseCase
import com.baykal.edumyclient.data.domain.question.QuestionAnswersUseCase
import com.baykal.edumyclient.data.domain.question.QuestionInformationUseCase
import com.baykal.edumyclient.data.model.answer.Answer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestionDetailViewModel @Inject constructor(
    private val session: EdumySession,
    private val questionInformationUseCase: QuestionInformationUseCase,
    private val questionAnswersUseCase: QuestionAnswersUseCase,
    private val upVoteAnswerUseCase: UpVoteAnswerUseCase,
    private val downVoteAnswerUseCase: DownVoteAnswerUseCase
) : BaseViewModel() {

    private var questionId: String? = null

    private val _uiState = MutableStateFlow(QuestionDetailState())
    val uiState = _uiState.asStateFlow()

    fun setImageDialog(dialogState: Boolean) {
        _uiState.update { it.copy(imageDialogState = dialogState) }
    }

    fun setImageUri(uri: String) {
        _uiState.update { it.copy(imageUri = uri, imageDialogState = true) }
    }

    fun fetchData() {
        args?.getString(QuestionDetailRoute.QUESTION_ID)?.let { id ->
            questionId = id
            questionInformationUseCase.observe(id).collectData { response ->
                response?.let { question ->
                    _uiState.update { it.copy(question = question) }
                }
            }
            session.withUserId { userId ->
                _uiState.update { it.copy(userId = userId) }
            }
        }
    }

    fun fetchAnswers(questionId: String, loading: Boolean = true) {
        questionAnswersUseCase.observe(questionId).collectData(loading) { response ->
            response?.let { list ->
                _uiState.update { it.copy(answers = list) }
            }
        }
    }

    fun upVoteAnswer(answer: Answer) {
        session.withUserId { userId ->
            answer.id?.let { answerId ->
                upVoteAnswerUseCase.observe(
                    UpVoteAnswerUseCase.Params(answerId, userId)
                ).collectData(false) {
                    fetchAnswers(questionId.toString(), false)
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
                    fetchAnswers(questionId.toString(), false)
                }
            }
        }
    }
}
