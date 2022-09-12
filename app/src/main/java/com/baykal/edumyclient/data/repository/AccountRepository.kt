package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.request.PasswordCredentials
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.model.user.request.UpdateCredentials
import com.baykal.edumyclient.data.service.EdumyServiceImp
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val service: EdumyServiceImp
) : BaseRepository() {

    fun loginUser(loginCredentials: LoginCredentials) = fetch {
        service.loginUser(loginCredentials)
    }

    fun registerUser(registerCredentials: RegisterCredentials) = fetch {
        service.registerUser(registerCredentials)
    }

    fun updateUser(updateCredentials: UpdateCredentials) = fetch {
        service.updateUser(updateCredentials)
    }

    fun changePassword(passwordCredentials: PasswordCredentials) = fetch {
        service.changePassword(passwordCredentials)
    }

    fun getUser(userId: String) = fetch {
        service.getUserInformation(userId)
    }

}