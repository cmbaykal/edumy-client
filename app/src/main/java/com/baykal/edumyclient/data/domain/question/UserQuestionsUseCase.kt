package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.repository.QuestionRepository
import javax.inject.Inject

class UserQuestionsUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : BaseUseCase<UserQuestionsUseCase.Params, BaseResult<ApiResponse<MutableList<Question>>>>() {

    override fun build(params: Params) = questionRepository.getUserQuestions(params.userId, params.page, params.limit)

    data class Params(val userId: String, val page: Int, val limit: Int)
}