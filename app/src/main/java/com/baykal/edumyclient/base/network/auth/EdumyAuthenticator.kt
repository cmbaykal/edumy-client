package com.baykal.edumyclient.base.network.auth

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
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
        return session.withUserId {
            runBlocking {
                authenticationRepository.fetchToken(it)
            }
        }?.let {
            session.saveToken(it)
            response.request.newBuilder()
                .header("Authorization", "Bearer ${it.token}")
                .build()
        } ?: run {
            null
        }
    }
}