package com.baykal.edumyclient.jUnit.repository

import com.baykal.edumyclient.data.model.study.request.StudyBody
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
class StudyRepositoryTest {

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
                    "/study/add" -> {
                        respond(SendStudyMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/study/delete" -> {
                        respond(DeleteStudyMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/study/feed" -> {
                        respond(StudiesMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/study/user" -> {
                        respond(UserStudiesMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/study/classroom" -> {
                        respond(ClassroomStudiesMockResponse(), HttpStatusCode.OK, responseHeaders)
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
    fun testAddStudy() = runBlocking {
        val body = StudyBody(userId = "", lesson = "", correctA = "", wrongA = "", emptyQ = "", date = "")
        val requestResult = service.sendStudy(body)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(SendStudyMockResponse())
    }

    @Test
    fun testDeleteStudy() = runBlocking {
        val requestResult = service.deleteStudy("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(DeleteStudyMockResponse())
    }

    @Test
    fun testStudies() = runBlocking {
        val requestResult = service.getStudies("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(StudiesMockResponse())
    }

    @Test
    fun testUserStudies() = runBlocking {
        val requestResult = service.getUserStudies("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(UserStudiesMockResponse())
    }

    @Test
    fun testClassroomStudies() = runBlocking {
        val requestResult = service.getClassroomStudies("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(ClassroomStudiesMockResponse())
    }
}

