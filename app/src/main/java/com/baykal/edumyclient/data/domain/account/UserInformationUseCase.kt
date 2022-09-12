package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.response.User
import com.baykal.edumyclient.data.repository.AccountRepository
import javax.inject.Inject

class UserInformationUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<String, User>() {

    override fun build(params: String) = accountRepository.getUser(params)

}