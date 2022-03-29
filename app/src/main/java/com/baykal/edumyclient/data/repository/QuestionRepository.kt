package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.service.EdumyService
import okhttp3.MultipartBody
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val service: EdumyService
) : BaseRepository() {

    fun askQuestion(body: MultipartBody) = fetch {
        service.addQuestion(body)
    }

    fun getQuestions(page: Int, limit: Int) = fetch {
        service.getQuestions(page, limit)
    }

    fun getUserQuestions(
        userId: String,
        page: Int,
        limit: Int
    ) = fetch {
        service.getUserQuestions(userId, page, limit)
    }

    fun getClassroomQuestions(
        classId: String,
        page: Int,
        limit: Int
    ) = fetch {
        service.getClassroomQuestions(classId, page, limit)
    }

    fun getQuestionInformation(questionId: String) = fetch {
        service.getQuestionInformation(questionId)
    }

    fun getQuestionAnswers(questionId: String) = fetch {
        service.getQuestionAnswers(questionId)
    }
}