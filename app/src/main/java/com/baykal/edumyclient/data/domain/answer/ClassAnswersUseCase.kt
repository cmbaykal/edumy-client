package com.baykal.edumyclient.data.domain.answer

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.repository.AnswerRepository
import javax.inject.Inject

class ClassAnswersUseCase @Inject constructor(
    private val answerRepository: AnswerRepository
) : BaseUseCase<String, MutableList<Answer>>() {

    override fun build(params: String) = answerRepository.getClassAnswers(params)
}