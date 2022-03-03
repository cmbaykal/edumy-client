package com.baykal.edumyclient.base.network.auth

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.user.response.AuthTokenResponse
import com.baykal.edumyclient.data.service.auth.AuthService
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val service: AuthService
) : BaseRepository() {

    suspend fun fetchToken(userId: String): AuthTokenResponse? {
        return runCatching {
            service.getAuthToken(userId).data
        }.getOrNull()
    }
}