package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.question.Question
import io.ktor.http.content.*
import kotlinx.coroutines.flow.Flow

abstract class QuestionRepository : BaseRepository() {
    abstract fun addQuestion(parts: List<PartData>): Flow<ApiResponse<out Unit>>
    abstract fun deleteQuestion(questionId: String): Flow<ApiResponse<out Unit>>
    abstract fun getQuestions(page: Int, limit: Int): Flow<ApiResponse<out MutableList<Question>>>
    abstract fun getClassroomQuestions(classId: String, page: Int, limit: Int): Flow<ApiResponse<out MutableList<Question>>>
    abstract fun getUserQuestions(userId: String, page: Int, limit: Int): Flow<ApiResponse<out MutableList<Question>>>
    abstract fun getQuestionInformation(questionId: String): Flow<ApiResponse<out Question>>
}