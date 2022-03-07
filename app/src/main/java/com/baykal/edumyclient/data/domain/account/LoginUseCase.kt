package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.response.User
import com.baykal.edumyclient.data.repository.AccountRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<LoginCredentials, BaseResult<ApiResponse<User>>>() {

    override fun build(params: LoginCredentials) = accountRepository.loginUser(params)

}