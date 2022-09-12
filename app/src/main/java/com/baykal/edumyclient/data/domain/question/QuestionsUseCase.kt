package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.repository.QuestionRepository
import javax.inject.Inject

class QuestionsUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : BaseUseCase<QuestionsUseCase.Params, MutableList<Question>>() {

    override fun build(params: Params) = questionRepository.getQuestions(params.page, params.limit)

    data class Params(val page: Int, val limit: Int)
}