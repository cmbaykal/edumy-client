package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.service.EdumyService
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val service: EdumyService
) : BaseRepository() {

    fun loginUser(loginCredentials: LoginCredentials) = fetch {
        service.loginUser(loginCredentials)
    }

    fun registerUser(registerCredentials: RegisterCredentials) = fetch {
        service.registerUser(registerCredentials)
    }

}