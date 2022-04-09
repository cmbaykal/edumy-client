package com.baykal.edumyclient.data.service

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.meeting.request.MeetingBody
import com.baykal.edumyclient.data.model.meeting.response.Meeting
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.study.request.StudyBody
import com.baykal.edumyclient.data.model.study.response.Study
import com.baykal.edumyclient.data.model.usage.UsageData
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.request.PasswordCredentials
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

    @POST("/user/changePassword")
    suspend fun changePassword(
        @Body passwordCredentials: PasswordCredentials
    ): BaseResult<ApiResponse<Unit>>

    @POST("user/info")
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
        @Query("classId") classId: String?,
        @Query("userMail") userMail: String
    ): BaseResult<ApiResponse<Unit>>

    @POST("/class/info")
    suspend fun getClassroomInformation(
        @Query("classId") classId: String
    ): BaseResult<ApiResponse<Classroom>>

    @POST("/class/user")
    suspend fun getUserClassrooms(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<MutableList<Classroom>>>

    @POST("/class/leave")
    suspend fun leaveClassroom(
        @Query("classId") classId: String,
        @Query("userMail") userMail: String?
    ): BaseResult<ApiResponse<Unit>>

    @POST("/class/delete")
    suspend fun deleteClassroom(
        @Query("classId") classId: String,
        @Query("userMail") userMail: String?
    ): BaseResult<ApiResponse<Unit>>

    // endregion

    // region Questions

    @POST("/question/add")
    suspend fun addQuestion(
        @Body body: MultipartBody
    ): BaseResult<ApiResponse<Unit>>

    @POST("/question/delete")
    suspend fun deleteQuestion(
        @Query("questionId") questionId: String
    ): BaseResult<ApiResponse<Unit>>

    @POST("/question/feed")
    suspend fun getQuestions(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResult<ApiResponse<MutableList<Question>>>

    @POST("/question/class")
    suspend fun getClassroomQuestions(
        @Query("classId") classId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResult<ApiResponse<MutableList<Question>>>

    @POST("/question/user")
    suspend fun getUserQuestions(
        @Query("userId") userId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResult<ApiResponse<MutableList<Question>>>

    @POST("/question/info")
    suspend fun getQuestionInformation(
        @Query("questionId") questionId: String
    ): BaseResult<ApiResponse<Question>>

    // endregion

    // region Answers

    @POST("/answer/add")
    suspend fun sendAnswer(
        @Body body: MultipartBody
    ): BaseResult<ApiResponse<Unit>>

    @POST("/answer/delete")
    suspend fun deleteAnswer(
        @Query("answerId") answerId: String
    ): BaseResult<ApiResponse<Unit>>

    @POST("/answer/upvote")
    suspend fun upVoteAnswer(
        @Query("answerId") answerId: String,
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<Unit>>

    @POST("/answer/downvote")
    suspend fun downVoteAnswer(
        @Query("answerId") answerId: String,
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<Unit>>

    @POST("/answer/question")
    suspend fun getQuestionAnswers(
        @Query("questionId") questionId: String
    ): BaseResult<ApiResponse<MutableList<Answer>>>

    @POST("/answer/user")
    suspend fun getUserAnswers(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<MutableList<Answer>>>

    @POST("/answer/class")
    suspend fun getClassAnswers(
        @Query("classId") classId: String
    ): BaseResult<ApiResponse<MutableList<Answer>>>

    // endregion

    // region Studies

    @POST("/study/add")
    suspend fun sendStudy(
        @Body studyBody: StudyBody
    ): BaseResult<ApiResponse<Unit>>

    @POST("/study/delete")
    suspend fun deleteStudy(
        @Query("studyId") studyId: String
    ): BaseResult<ApiResponse<Unit>>

    @POST("/study/user")
    suspend fun getUserStudies(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<MutableList<Study>>>
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

    // region Meetings

    @POST("/meeting/schedule")
    suspend fun scheduleMeeting(
        @Body meetingBody: MeetingBody
    ): BaseResult<ApiResponse<Unit>>

    @POST("/meeting/user")
    suspend fun getUserMeetings(
        @Query("userId") userId: String
    ): BaseResult<ApiResponse<MutableList<Meeting>>>

    // endregion Meetings

}