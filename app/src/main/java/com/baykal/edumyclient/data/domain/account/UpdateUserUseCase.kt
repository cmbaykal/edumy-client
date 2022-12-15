package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.UpdateCredentials
import com.baykal.edumyclient.data.repository.AccountRepositoryImpl
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val accountRepository: AccountRepositoryImpl
) : BaseUseCase<UpdateCredentials, Unit>() {

    override fun build(params: UpdateCredentials) = accountRepository.updateUser(params)

}