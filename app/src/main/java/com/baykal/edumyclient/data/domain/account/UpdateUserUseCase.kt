package com.baykal.edumyclient.data.domain.account

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.user.request.UpdateCredentials
import com.baykal.edumyclient.data.repository.AccountRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<UpdateCredentials, Unit>() {

    override fun build(params: UpdateCredentials) = accountRepository.updateUser(params)

}