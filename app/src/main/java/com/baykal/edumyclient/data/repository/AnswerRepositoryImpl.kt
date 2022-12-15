package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.data.service.EdumyServiceImp
import io.ktor.http.content.*
import javax.inject.Inject

class AnswerRepositoryImpl @Inject constructor(
    private val service: EdumyServiceImp
) : AnswerRepository() {

    override fun sendAnswer(parts: List<PartData>) = fetch {
        service.sendAnswer(parts)
    }

    override fun deleteAnswer(answerId: String) = fetch {
        service.deleteAnswer(answerId)
    }

    override fun getUserAnswers(userId: String) = fetch {
        service.getUserAnswers(userId)
    }

    override fun getClassroomAnswers(classId: String) = fetch {
        service.getClassroomAnswers(classId)
    }

    override fun getQuestionAnswers(questionId: String) = fetch {
        service.getQuestionAnswers(questionId)
    }

    override fun upVoteAnswer(answerId: String, userId: String) = fetch {
        service.upVoteAnswer(answerId, userId)
    }

    override fun downVoteAnswer(answerId: String, userId: String) = fetch {
        service.downVoteAnswer(answerId, userId)
    }
}