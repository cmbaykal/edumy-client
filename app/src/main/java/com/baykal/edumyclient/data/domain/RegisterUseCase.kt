package com.baykal.edumyclient.data.domain

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.repository.AccountRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<RegisterCredentials, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: RegisterCredentials) = accountRepository.registerUser(params)

}