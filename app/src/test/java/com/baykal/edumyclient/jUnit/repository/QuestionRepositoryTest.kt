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
class QuestionRepositoryTest {

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
                    "/question/add" -> {
                        respond(AddQuestionMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/question/delete" -> {
                        respond(DeleteQuestionMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/question/feed" -> {
                        respond(QuestionsMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/question/classroom" -> {
                        respond(ClassroomQuestionsMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/question/user" -> {
                        respond(UserQuestionsMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/question/info" -> {
                        respond(QuestionInformationMockResponse(), HttpStatusCode.OK, responseHeaders)
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
    fun testAddQuestion() = runBlocking {
        val requestResult = service.addQuestion(listOf())
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(AddQuestionMockResponse())
    }

    @Test
    fun testDeleteQuestion() = runBlocking {
        val requestResult = service.deleteQuestion("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(DeleteQuestionMockResponse())
    }

    @Test
    fun testQuestions() = runBlocking {
        val requestResult = service.getQuestions(0, 0)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(QuestionsMockResponse())
    }

    @Test
    fun testClassroomQuestions() = runBlocking {
        val requestResult = service.getClassroomQuestions("", 0, 0)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(ClassroomQuestionsMockResponse())
    }

    @Test
    fun testUserQuestions() = runBlocking {
        val requestResult = service.getUserQuestions("", 0, 0)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(UserQuestionsMockResponse())
    }

    @Test
    fun testQuestionInformation() = runBlocking {
        val requestResult = service.getQuestionInformation("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(QuestionInformationMockResponse())
    }
}

