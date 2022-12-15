package com.baykal.edumyclient.jUnit.repository.mock

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.meeting.response.Meeting
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ScheduleMeetingMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object UserMeetingsMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Meeting>()))
    }
}