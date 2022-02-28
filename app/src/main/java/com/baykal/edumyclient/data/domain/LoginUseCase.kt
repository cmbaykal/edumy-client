package com.baykal.edumyclient.data.domain

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<LoginCredentials, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: LoginCredentials) = loginRepository.loginUser(params)

}