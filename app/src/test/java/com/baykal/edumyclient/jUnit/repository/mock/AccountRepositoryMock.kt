package com.baykal.edumyclient.jUnit.repository.mock

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.user.response.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object LoginMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object RegisterMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object UpdateMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object ChangePasswordMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object UserInfoMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(User(id = null, role = null, mail = null, birth = null, name = null, bio = null, classes = null)))
    }
}