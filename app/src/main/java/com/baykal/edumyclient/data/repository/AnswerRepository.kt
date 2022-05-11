package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.service.EdumyService
import okhttp3.MultipartBody
import javax.inject.Inject

class AnswerRepository @Inject constructor(
    private val service: EdumyService
) : BaseRepository() {

    fun sendAnswer(body: MultipartBody) = fetch {
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