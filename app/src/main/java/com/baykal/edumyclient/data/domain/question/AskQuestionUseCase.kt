package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.QuestionRepositoryImpl
import io.ktor.http.content.*
import javax.inject.Inject

class AskQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepositoryImpl
) : BaseUseCase<List<PartData>, Unit>() {

    override fun build(params: List<PartData>) = questionRepository.addQuestion(params)
}