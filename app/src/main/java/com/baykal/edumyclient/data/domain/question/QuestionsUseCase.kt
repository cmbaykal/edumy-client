package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.repository.QuestionRepositoryImpl
import javax.inject.Inject

class QuestionsUseCase @Inject constructor(
    private val questionRepository: QuestionRepositoryImpl
) : BaseUseCase<QuestionsUseCase.Params, MutableList<Question>>() {

    override fun build(params: Params) = questionRepository.getQuestions(params.page, params.limit)

    data class Params(val page: Int, val limit: Int)
}