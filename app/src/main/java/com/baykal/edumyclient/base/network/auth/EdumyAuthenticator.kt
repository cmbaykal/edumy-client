package com.baykal.edumyclient.base.network.auth

import com.baykal.edumyclient.base.preference.EdumySession
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EdumyAuthenticator @Inject constructor(
    private val session: EdumySession,
    private val authenticationRepository: AuthenticationRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return if (response.request.header("No-Auth").isNullOrEmpty()) {
            var token = session.token

            if (token == null) {
                session.userId?.let {
                    runBlocking {
                        authenticationRepository.fetchToken(it)
                    }
                }?.let {
                    session.saveToken(it)
                    token = it.token
                }
            }

            response.request.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            null
        }
    }
}