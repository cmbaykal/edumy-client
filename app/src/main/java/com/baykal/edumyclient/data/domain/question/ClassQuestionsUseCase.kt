package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.repository.QuestionRepository
import javax.inject.Inject

class ClassQuestionsUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : BaseUseCase<ClassQuestionsUseCase.Params, BaseResult<ApiResponse<MutableList<Question>>>>() {

    override fun build(params: Params) = questionRepository.getClassroomQuestions(params.userId, params.page, params.limit)

    data class Params(val userId: String, val page: Int, val limit: Int)
}