package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.repository.QuestionRepository
import javax.inject.Inject

class QuestionInformationUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : BaseUseCase<String, BaseResult<ApiResponse<Question>>>() {

    override fun build(params: String) = questionRepository.getQuestionInformation(params)
}