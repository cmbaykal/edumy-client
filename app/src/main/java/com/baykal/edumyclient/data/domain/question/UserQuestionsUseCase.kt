package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.repository.QuestionRepositoryImpl
import javax.inject.Inject

class UserQuestionsUseCase @Inject constructor(
    private val questionRepository: QuestionRepositoryImpl
) : BaseUseCase<UserQuestionsUseCase.Params, MutableList<Question>>() {

    override fun build(params: Params) = questionRepository.getUserQuestions(params.userId, params.page, params.limit)

    data class Params(val userId: String, val page: Int, val limit: Int)
}