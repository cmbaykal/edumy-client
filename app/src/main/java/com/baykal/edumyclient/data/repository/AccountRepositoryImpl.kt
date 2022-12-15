package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.request.PasswordCredentials
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.model.user.request.UpdateCredentials
import com.baykal.edumyclient.data.service.EdumyServiceImp
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val service: EdumyServiceImp
) : AccountRepository() {

    override fun loginUser(loginCredentials: LoginCredentials) = fetch {
        service.loginUser(loginCredentials)
    }

    override fun registerUser(registerCredentials: RegisterCredentials) = fetch {
        service.registerUser(registerCredentials)
    }

    override fun updateUser(updateCredentials: UpdateCredentials) = fetch {
        service.updateUser(updateCredentials)
    }

    override fun changePassword(passwordCredentials: PasswordCredentials) = fetch {
        service.changePassword(passwordCredentials)
    }

    override fun getUserInformation(userId: String) = fetch {
        service.getUserInformation(userId)
    }
}