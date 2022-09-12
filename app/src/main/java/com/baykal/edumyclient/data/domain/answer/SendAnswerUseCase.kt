package com.baykal.edumyclient.data.domain.answer

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.AnswerRepository
import io.ktor.http.content.*
import javax.inject.Inject

class SendAnswerUseCase @Inject constructor(
    private val answerRepository: AnswerRepository
) : BaseUseCase<List<PartData>, Unit>() {

    override fun build(params: List<PartData>) = answerRepository.sendAnswer(params)
}