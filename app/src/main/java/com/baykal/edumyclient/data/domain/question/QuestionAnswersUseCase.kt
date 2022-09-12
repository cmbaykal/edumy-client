package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.repository.QuestionRepository
import javax.inject.Inject

class QuestionAnswersUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : BaseUseCase<String, MutableList<Answer>>() {

    override fun build(params: String) = questionRepository.getQuestionAnswers(params)
}