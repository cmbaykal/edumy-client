package com.baykal.edumyclient.data.domain.answer

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.AnswerRepositoryImpl
import javax.inject.Inject

class DownVoteAnswerUseCase @Inject constructor(
    private val answerRepository: AnswerRepositoryImpl
) : BaseUseCase<DownVoteAnswerUseCase.Params, Unit>() {

    override fun build(params: Params) = answerRepository.downVoteAnswer(params.answerId, params.userId)

    data class Params(val answerId: String, val userId: String)
}