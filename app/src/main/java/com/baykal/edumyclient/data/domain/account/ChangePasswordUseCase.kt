package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.PasswordCredentials
import com.baykal.edumyclient.data.repository.AccountRepositoryImpl
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepositoryImpl
) : BaseUseCase<PasswordCredentials, Unit>() {

    override fun build(params: PasswordCredentials) = accountRepository.changePassword(params)

}