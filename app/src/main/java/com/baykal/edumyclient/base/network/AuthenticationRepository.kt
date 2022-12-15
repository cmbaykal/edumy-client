package com.baykal.edumyclient.base.network

import com.baykal.edumyclient.data.model.user.response.AuthTokenResponse
import com.baykal.edumyclient.data.service.AuthServiceImp
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val service: AuthServiceImp
) {

    suspend fun fetchToken(userId: String): AuthTokenResponse? {
        return runCatching {
            service.getAuthToken(userId).data
        }.getOrNull()
    }
}