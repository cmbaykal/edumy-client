package com.baykal.edumyclient.data.service

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.performance.request.PerformanceBody
import com.baykal.edumyclient.data.model.performance.response.Performance
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.usage.UsageData
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.model.user.request.UpdateCredentials
import com.baykal.edumyclient.data.model.user.response.User
import okhttp3.MultipartBody
import retrofit2.http.*

interface EdumyService {

    // region User
    @Headers("No-Auth:true")
    @POST("/user/register")
    suspend fun registerUser(
        @Body registerCredentials: RegisterCredentials
    ): BaseResult<ApiResponse<Unit>>

    @Headers("No-Auth:true")
    @POST("/user/login")
    suspend fun loginUser(
        @Body loginCredentials: LoginCredentials
    ): BaseResult<ApiResponse<User>>

    @POST("/user/update")
    suspend fun updateUser(
        @Body updateCredentials: UpdateCredentials
    ): BaseResult<ApiResponse<Unit>>

    @GET("user/info")
    suspend fun getUserInformation(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<User>>

    // endregion

    // region Classrooms

    @POST("/class/add")
    suspend fun addClassroom(
        @Body classroom: ClassroomBody
    ): BaseResult<ApiResponse<Unit>>

    @POST("/class/assign")
    suspend fun assignUserToClassroom(
        @Query("classId") classId: String,
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<Unit>>

    @POST("/class/leave")
    suspend fun leaveClassroom(
        @Query("classId") classId: String,
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<Unit>>

    @GET("/class/info")
    suspend fun getClassroomInformation(
        @Query("classId") classId: String
    ): BaseResult<ApiResponse<Classroom>>

    @POST("/class/user")
    suspend fun getUserClassrooms(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<MutableList<Classroom>>>

    @POST("/class/delete")
    suspend fun deleteClassroom(
        @Query("classId") classId: String
    ): BaseResult<ApiResponse<Unit>>

    // endregion

    // region Questions

    @Multipart
    @POST("/question/add")
    suspend fun addQuestion(
        @Part("classId") classId: String,
        @Part("userId") userId: String,
        @Part("lesson") lesson: String,
        @Part("question") question: String,
        @Part("date") date: String,
        @Part image: MultipartBody.Part? = null
    ): BaseResult<ApiResponse<Unit>>

    @POST("/question/delete")
    suspend fun deleteQuestion(
        @Query("questionId") questionId: String
    ): BaseResult<ApiResponse<Unit>>

    @GET("/question/info")
    suspend fun getQuestionInformation(
        @Query("questionId") questionId: String
    ): BaseResult<ApiResponse<Question>>

    @GET("/question/class")
    suspend fun getClassroomQuestions(
        @Query("classId") classId: String
    ): BaseResult<ApiResponse<MutableList<Question>>>

    @GET("/question/user")
    suspend fun getUserQuestions(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<MutableList<Question>>>

    // endregion

    // region Answers

    @Multipart
    @POST("/answer/add")
    suspend fun addAnswer(
        @Part("questionId") questionId: String,
        @Part("userId") userId: String,
        @Part("date") date: String,
        @Part image: MultipartBody.Part? = null,
        @Part video: MultipartBody.Part? = null
    ): BaseResult<ApiResponse<Unit>>

    @POST("/answer/delete")
    suspend fun deleteAnswer(
        @Query("answerId") answerId: String
    ): BaseResult<ApiResponse<Unit>>

    @GET("/answer/question")
    suspend fun getQuestionAnswers(
        @Query("questionId") questionId: String
    ): BaseResult<ApiResponse<MutableList<Answer>>>

    @GET("/answer/user")
    suspend fun getUserAnswers(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<MutableList<Answer>>>

    // endregion

    // region Performances

    @POST("/performance/add")
    suspend fun addPerformance(
        @Body performanceBody: PerformanceBody
    ): BaseResult<ApiResponse<Unit>>

    @POST("/performance/delete")
    suspend fun deletePerformance(
        @Query("performanceId") performanceId: String
    ): BaseResult<ApiResponse<Unit>>

    @GET("/performance/user")
    suspend fun getUserPerformances(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<MutableList<Performance>>>

    // endregion

    // region Usages

    @POST("/usage/add")
    suspend fun addUsage(
        @Body usageBody: UsageData
    ): BaseResult<ApiResponse<Unit>>

    @GET("/usage/{userId}")
    suspend fun getUserUsages(
        @Path("userId") userId: String
    ): BaseResult<ApiResponse<UsageData>>

    // endregion

}