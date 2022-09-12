package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.service.EdumyServiceImp
import io.ktor.http.content.*
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val service: EdumyServiceImp
) : BaseRepository() {

    fun askQuestion(body: List<PartData>) = fetch {
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