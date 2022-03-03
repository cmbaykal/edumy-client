package com.baykal.edumyclient.base.network.auth

import com.baykal.edumyclient.base.preference.EdumySession
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val session: EdumySession
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (request.header("No-Auth").isNullOrEmpty()) {
            request = chain.request().newBuilder().apply {
                session.token?.let { header("Authorization", "Bearer $it") }
            }.build()
        }
        return chain.proceed(request)
    }
}