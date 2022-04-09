package com.baykal.edumyclient.data.domain.answer

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.AnswerRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class SendAnswerUseCase @Inject constructor(
    private val answerRepository: AnswerRepository
) : BaseUseCase<MultipartBody, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: MultipartBody) = answerRepository.sendAnswer(params)
}