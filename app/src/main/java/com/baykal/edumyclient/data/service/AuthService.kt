package com.baykal.edumyclient.data.service

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.user.response.AuthTokenResponse

interface AuthService {

    suspend fun getAuthToken(userId: String): ApiResponse<AuthTokenResponse>

}