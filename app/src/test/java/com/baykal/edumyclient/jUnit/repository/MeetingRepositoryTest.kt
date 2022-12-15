package com.baykal.edumyclient.jUnit.repository

import com.baykal.edumyclient.data.model.meeting.request.MeetingBody
import com.baykal.edumyclient.data.service.EdumyServiceImp
import com.baykal.edumyclient.jUnit.repository.mock.ScheduleMeetingMockResponse
import com.baykal.edumyclient.jUnit.repository.mock.UserMeetingsMockResponse
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
class MeetingRepositoryTest {

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
                    "/meeting/schedule" -> {
                        respond(ScheduleMeetingMockResponse(), HttpStatusCode.OK, responseHeaders)
                    }
                    "/meeting/user" -> {
                        respond(UserMeetingsMockResponse(), HttpStatusCode.OK, responseHeaders)
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
    fun testScheduleMeeting() = runBlocking {
        val body = MeetingBody(classId = "", creatorId = "", description = "", duration = 0, date = "")
        val requestResult = service.scheduleMeeting(body)
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(ScheduleMeetingMockResponse())
    }

    @Test
    fun testUserMeetings() = runBlocking {
        val requestResult = service.getUserMeetings("")
        val response = Json.encodeToString(requestResult)
        Truth.assertThat(response).isEqualTo(UserMeetingsMockResponse())
    }
}

