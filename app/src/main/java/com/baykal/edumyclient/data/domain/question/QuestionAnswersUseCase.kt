package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.repository.AnswerRepositoryImpl
import javax.inject.Inject

class QuestionAnswersUseCase @Inject constructor(
    private val answerRepository: AnswerRepositoryImpl
) : BaseUseCase<String, MutableList<Answer>>() {

    override fun build(params: String) = answerRepository.getQuestionAnswers(params)
}