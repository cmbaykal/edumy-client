package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.answer.Answer
import io.ktor.http.content.*
import kotlinx.coroutines.flow.Flow

abstract class AnswerRepository : BaseRepository() {
    abstract fun sendAnswer(parts: List<PartData>): Flow<ApiResponse<out Unit>>
    abstract fun deleteAnswer(answerId: String): Flow<ApiResponse<out Unit>>
    abstract fun upVoteAnswer(answerId: String, userId: String): Flow<ApiResponse<out Unit>>
    abstract fun downVoteAnswer(answerId: String, userId: String): Flow<ApiResponse<out Unit>>
    abstract fun getQuestionAnswers(questionId: String): Flow<ApiResponse<out MutableList<Answer>>>
    abstract fun getUserAnswers(userId: String): Flow<ApiResponse<out MutableList<Answer>>>
    abstract fun getClassroomAnswers(classId: String): Flow<ApiResponse<out MutableList<Answer>>>
}