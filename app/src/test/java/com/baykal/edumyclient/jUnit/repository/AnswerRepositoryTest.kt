package com.baykal.edumyclient.jUnit.repository

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
class AnswerRepositoryTest {

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
                    "/answer/add" -> {
                        respond(SendAnswerMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/answer/delete" -> {
                        respond(DeleteAnswerMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/answer/upvote" -> {
                        respond(UpVoteAnswerMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/answer/downvote" -> {
                        respond(DownVoteAnswerMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/answer/question" -> {
                        respond(QuestionAnswersMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/answer/user" -> {
                        respond(UserAnswersMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/answer/classroom" -> {
                        respond(ClassroomAnswersMockResponse(), HttpStatusCode.OK, responseHeaders)
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
    fun testAddAnswer() = runBlocking {
        val requestResult = service.sendAnswer(listOf())
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(SendAnswerMockResponse())
    }

    @Test
    fun testDeleteAnswer() = runBlocking {
        val requestResult = service.deleteAnswer("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(DeleteAnswerMockResponse())
    }

    @Test
    fun testUpVoteAnswer() = runBlocking {
        val requestResult = service.upVoteAnswer("", "")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(UpVoteAnswerMockResponse())
    }

    @Test
    fun testDownVoteAnswer() = runBlocking {
        val requestResult = service.downVoteAnswer("", "")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(DownVoteAnswerMockResponse())
    }

    @Test
    fun testQuestionAnswers() = runBlocking {
        val requestResult = service.getQuestionAnswers("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(QuestionAnswersMockResponse())
    }

    @Test
    fun testUserAnswers() = runBlocking {
        val requestResult = service.getUserAnswers("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(UserAnswersMockResponse())
    }

    @Test
    fun testClassroomAnswers() = runBlocking {
        val requestResult = service.getClassroomAnswers("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(ClassroomAnswersMockResponse())
    }

}

