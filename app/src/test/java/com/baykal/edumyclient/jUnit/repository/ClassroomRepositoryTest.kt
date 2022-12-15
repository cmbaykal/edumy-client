package com.baykal.edumyclient.jUnit.repository

import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
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
class ClassroomRepositoryTest {

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
                    "/class/add" -> {
                        respond(AddClassroomMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/class/assign" -> {
                        respond(AssingUserToClassroomMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/class/info" -> {
                        respond(ClassroomInformationMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/class/user" -> {
                        respond(UserClassroomsMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/class/leave" -> {
                        respond(LeaveClassroomMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/class/delete" -> {
                        respond(DeleteClassroomMockResponse(), HttpStatusCode.OK, responseHeaders)
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
    fun testAddClassroom() = runBlocking {
        val body = ClassroomBody(lesson = "", name = "", creatorId = "")
        val requestResult = service.addClassroom(body)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(AddClassroomMockResponse())
    }

    @Test
    fun testAssignUserToClassroom() = runBlocking {
        val requestResult = service.assignUserToClassroom("", "")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(AssingUserToClassroomMockResponse())
    }

    @Test
    fun testClassroomInformation() = runBlocking {
        val requestResult = service.getClassroomInformation("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(ClassroomInformationMockResponse())
    }

    @Test
    fun testUserClassrooms() = runBlocking {
        val requestResult = service.getUserClassrooms("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(UserClassroomsMockResponse())
    }

    @Test
    fun testLeaveClassroom() = runBlocking {
        val requestResult = service.leaveClassroom("", "")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(LeaveClassroomMockResponse())
    }

    @Test
    fun testDeleteClassroom() = runBlocking {
        val requestResult = service.deleteClassroom("", "")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(DeleteClassroomMockResponse())
    }
}

