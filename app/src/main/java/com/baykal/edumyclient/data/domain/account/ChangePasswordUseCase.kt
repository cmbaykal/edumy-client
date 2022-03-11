package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.PasswordCredentials
import com.baykal.edumyclient.data.repository.AccountRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<PasswordCredentials, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: PasswordCredentials) = accountRepository.changePassword(params)

}