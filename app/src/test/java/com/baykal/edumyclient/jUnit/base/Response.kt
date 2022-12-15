package com.baykal.edumyclient.jUnit.base

import com.baykal.edumyclient.base.data.ApiResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ErrorMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.error("Failed"))
    }
}