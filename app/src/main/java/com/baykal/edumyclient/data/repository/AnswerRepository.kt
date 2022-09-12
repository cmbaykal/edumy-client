package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.service.EdumyServiceImp
import io.ktor.http.content.*
import javax.inject.Inject

class AnswerRepository @Inject constructor(
    private val service: EdumyServiceImp
) : BaseRepository() {

    fun sendAnswer(body: List<PartData>) = fetch {
        service.sendAnswer(body)
    }

    fun getUserAnswers(userId: String) = fetch {
        service.getUserAnswers(userId)
    }

    fun getClassAnswers(classId: String) = fetch {
        service.getClassroomAnswers(classId)
    }

    fun upVoteAnswer(answerId: String, userId: String) = fetch {
        service.upVoteAnswer(answerId, userId)
    }

    fun downVoteAnswer(answerId: String, userId: String) = fetch {
        service.downVoteAnswer(answerId, userId)
    }
}