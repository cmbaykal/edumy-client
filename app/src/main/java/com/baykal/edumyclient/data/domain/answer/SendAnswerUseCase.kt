package com.baykal.edumyclient.data.domain.answer

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.AnswerRepositoryImpl
import io.ktor.http.content.*
import javax.inject.Inject

class SendAnswerUseCase @Inject constructor(
    private val answerRepository: AnswerRepositoryImpl
) : BaseUseCase<List<PartData>, Unit>() {

    override fun build(params: List<PartData>) = answerRepository.sendAnswer(params)
}