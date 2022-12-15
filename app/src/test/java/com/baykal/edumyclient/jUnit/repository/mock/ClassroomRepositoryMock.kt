package com.baykal.edumyclient.jUnit.repository.mock

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object AddClassroomMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object AssingUserToClassroomMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object ClassroomInformationMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Classroom(id = null, lesson = null, name = null, creatorId = null, users = null)))
    }
}

object UserClassroomsMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Classroom>()))
    }
}

object LeaveClassroomMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}


object DeleteClassroomMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}