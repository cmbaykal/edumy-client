package com.baykal.edumyclient.data.service.auth

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.user.response.AuthTokenResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AuthService {

    @Headers("No-Auth:true")
    @GET("/user/auth/token")
    suspend fun getAuthToken(
        @Query("userId") userId: String
    ): ApiResponse<AuthTokenResponse>

}