package com.baykal.edumyclient.jUnit.repository.mock

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.answer.Answer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SendAnswerMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object DeleteAnswerMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object UpVoteAnswerMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object DownVoteAnswerMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object QuestionAnswersMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Answer>()))
    }
}

object UserAnswersMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Answer>()))
    }
}

object ClassroomAnswersMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Answer>()))
    }
}