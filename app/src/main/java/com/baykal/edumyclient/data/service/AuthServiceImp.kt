package com.baykal.edumyclient.data.service

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.user.response.AuthTokenResponse
import com.baykal.edumyclient.di.BASE_URL
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthServiceImp(
    private val httpClient: HttpClient
) : AuthService {

    override suspend fun getAuthToken(userId: String): ApiResponse<AuthTokenResponse> {
        return httpClient.get {
            url {
                host = BASE_URL
                path("/user/auth/token")
                parameter("userId", userId)
            }
            headers {
                append("No-Auth", "true")
            }
        }.body()
    }
}