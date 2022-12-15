package com.baykal.edumyclient.jUnit.repository.mock

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.study.response.Study
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SendStudyMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object DeleteStudyMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object StudiesMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Study>()))
    }
}

object UserStudiesMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Study>()))
    }
}

object ClassroomStudiesMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Study>()))
    }
}