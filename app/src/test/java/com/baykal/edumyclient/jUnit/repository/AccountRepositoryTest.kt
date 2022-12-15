package com.baykal.edumyclient.jUnit.repository

import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.request.PasswordCredentials
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.model.user.request.UpdateCredentials
import com.baykal.edumyclient.data.service.EdumyServiceImp
import com.baykal.edumyclient.jUnit.repository.mock.*
import com.google.common.truth.Truth
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AccountRepositoryTest {

    private val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
    private val client = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }
        engine {
            addHandler { request ->
                when (request.url.encodedPath) {
                    "/user/login" -> {
                        respond(LoginMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/user/register" -> {
                        respond(RegisterMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/user/update" -> {
                        respond(UpdateMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/user/changePassword" -> {
                        respond(ChangePasswordMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/user/info" -> {
                        respond(UserInfoMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    else -> {
                        error("Unhandled ${request.url.encodedPath}")
                    }
                }
            }
        }
    }

    val service = EdumyServiceImp(client)

    @Test
    fun testLoginSuccess() = runBlocking {
        val body = LoginCredentials(mail = "", pass = "")
        val requestResult = service.loginUser(body)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(LoginMockResponse())
    }

    @Test
    fun testRegister() = runBlocking {
        val body = RegisterCredentials(role = null, mail = null, birth = null, pass = null, name = null, bio = null)
        val requestResult = service.registerUser(body)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(RegisterMockResponse())
    }

    @Test
    fun testUpdate() = runBlocking {
        val body = UpdateCredentials(userId = "", name = null, bio = null)
        val requestResult = service.updateUser(body)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(UpdateMockResponse())
    }

    @Test
    fun testChangePassword() = runBlocking {
        val body = PasswordCredentials(userId = "", oldPass = "", newPass = "")
        val requestResult = service.changePassword(body)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(ChangePasswordMockResponse())
    }

    @Test
    fun testUserInformation() = runBlocking {
        val requestResult = service.getUserInformation("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(UserInfoMockResponse())
    }
}

