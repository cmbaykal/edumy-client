package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.response.User
import com.baykal.edumyclient.data.repository.AccountRepositoryImpl
import javax.inject.Inject

class UserInformationUseCase @Inject constructor(
    private val accountRepository: AccountRepositoryImpl
) : BaseUseCase<String, User>() {

    override fun build(params: String) = accountRepository.getUserInformation(params)
}