package com.baykal.edumyclient.data.domain.answer

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.AnswerRepository
import javax.inject.Inject

class UpVoteAnswerUseCase @Inject constructor(
    private val answerRepository: AnswerRepository
) : BaseUseCase<UpVoteAnswerUseCase.Params, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: Params) = answerRepository.upVoteAnswer(params.answerId, params.userId)

    data class Params(val answerId: String, val userId: String)
}