package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.data.service.EdumyServiceImp
import io.ktor.http.content.*
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val service: EdumyServiceImp
) : QuestionRepository() {

    override fun addQuestion(parts: List<PartData>) = fetch {
        service.addQuestion(parts)
    }

    override fun deleteQuestion(questionId: String) = fetch {
        service.deleteQuestion(questionId)
    }

    override fun getQuestions(page: Int, limit: Int) = fetch {
        service.getQuestions(page, limit)
    }

    override fun getUserQuestions(
        userId: String,
        page: Int,
        limit: Int
    ) = fetch {
        service.getUserQuestions(userId, page, limit)
    }

    override fun getClassroomQuestions(
        classId: String,
        page: Int,
        limit: Int
    ) = fetch {
        service.getClassroomQuestions(classId, page, limit)
    }

    override fun getQuestionInformation(questionId: String) = fetch {
        service.getQuestionInformation(questionId)
    }
}