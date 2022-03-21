package com.baykal.edumyclient.ui.screen.questionSection.questionDetail

import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.question.QuestionInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestionDetailViewModel @Inject constructor(
    private val questionInformationUseCase: QuestionInformationUseCase,
//    private val questionAnswersUseCase:QuestionAnswersUseCase
) : BaseViewModel() {

    private var questionId: String? = null

    private val _uiState = MutableStateFlow(QuestionDetailState())
    val uiState = _uiState.asStateFlow()

    fun fetchData() {
        args?.getString(QuestionDetailRoute.QUESTION_ID)?.let { id ->
            questionId = id
            questionInformationUseCase.observe(id).collectData { response ->
                response?.let { question ->
                    _uiState.update { it.copy(question = question) }
                }
            }
        }
    }
}