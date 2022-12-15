package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.repository.AccountRepositoryImpl
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val accountRepository: AccountRepositoryImpl
) : BaseUseCase<RegisterCredentials, Unit>() {

    override fun build(params: RegisterCredentials) = accountRepository.registerUser(params)

}