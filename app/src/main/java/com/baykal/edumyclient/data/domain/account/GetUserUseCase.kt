package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.response.User
import com.baykal.edumyclient.data.repository.AccountRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<String, BaseResult<ApiResponse<User>>>() {

    override fun build(params: String) = accountRepository.getUser(params)

}