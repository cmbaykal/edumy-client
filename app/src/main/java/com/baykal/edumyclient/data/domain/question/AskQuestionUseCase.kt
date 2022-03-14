package com.baykal.edumyclient.data.domain.question

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.QuestionRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class AskQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : BaseUseCase<MultipartBody, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: MultipartBody) = questionRepository.askQuestion(params)
}