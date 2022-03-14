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

    fun getUserQuestions(userId: String) = fetch {
        service.getUserQuestions(userId)
    }

    fun getClassroomQuestions(classId: String) = fetch {
        service.getClassroomQuestions(classId)
    }

    fun getQuestionInformation(questionId: String) = fetch {
        service.getQuestionInformation(questionId)
    }
}