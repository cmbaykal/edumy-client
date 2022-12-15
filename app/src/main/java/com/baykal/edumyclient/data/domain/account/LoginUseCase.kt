package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.response.User
import com.baykal.edumyclient.data.repository.AccountRepositoryImpl
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val accountRepository: AccountRepositoryImpl
) : BaseUseCase<LoginCredentials, User>() {

    override fun build(params: LoginCredentials) = accountRepository.loginUser(params)

}