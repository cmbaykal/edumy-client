package com.baykal.edumyclient.jUnit.repository.mock

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.data.model.question.Question
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object AddQuestionMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object DeleteQuestionMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Unit))
    }
}

object QuestionsMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Question>()))
    }
}

object ClassroomQuestionsMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Question>()))
    }
}

object UserQuestionsMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(mutableListOf<Question>()))
    }
}

object QuestionInformationMockResponse {
    operator fun invoke(): String {
        return Json.encodeToString(ApiResponse.success(Question(id = null, userId = null, user = null, lesson = null, description = null, date = null, image = null)))
    }
}