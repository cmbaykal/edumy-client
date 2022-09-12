package com.baykal.edumyclient.data.service

import com.baykal.edumyclient.base.data.ApiResponse
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
import io.ktor.http.content.*

interface EdumyService {

    // region User

    suspend fun registerUser(registerCredentials: RegisterCredentials): ApiResponse<Unit>
    suspend fun loginUser(loginCredentials: LoginCredentials): ApiResponse<User>
    suspend fun updateUser(updateCredentials: UpdateCredentials): ApiResponse<Unit>
    suspend fun changePassword(passwordCredentials: PasswordCredentials): ApiResponse<Unit>
    suspend fun getUserInformation(userId: String): ApiResponse<User>

    // endregion

    // region Classrooms

    suspend fun addClassroom(classroom: ClassroomBody): ApiResponse<Unit>
    suspend fun assignUserToClassroom(classId: String?, userMail: String): ApiResponse<Unit>
    suspend fun getClassroomInformation(classId: String): ApiResponse<Classroom>
    suspend fun getUserClassrooms(userId: String): ApiResponse<MutableList<Classroom>>
    suspend fun leaveClassroom(classId: String, userMail: String?): ApiResponse<Unit>
    suspend fun deleteClassroom(classId: String, userMail: String?): ApiResponse<Unit>

    // endregion

    // region Questions

    suspend fun addQuestion(parts: List<PartData>): ApiResponse<Unit>
    suspend fun deleteQuestion(questionId: String): ApiResponse<Unit>
    suspend fun getQuestions(page: Int, limit: Int): ApiResponse<MutableList<Question>>
    suspend fun getClassroomQuestions(classId: String, page: Int, limit: Int): ApiResponse<MutableList<Question>>
    suspend fun getUserQuestions(userId: String, page: Int, limit: Int): ApiResponse<MutableList<Question>>
    suspend fun getQuestionInformation(questionId: String): ApiResponse<Question>

    // endregion

    // region Answers

    suspend fun sendAnswer(parts: List<PartData>): ApiResponse<Unit>
    suspend fun deleteAnswer(answerId: String): ApiResponse<Unit>
    suspend fun upVoteAnswer(answerId: String, userId: String): ApiResponse<Unit>
    suspend fun downVoteAnswer(answerId: String, userId: String): ApiResponse<Unit>
    suspend fun getQuestionAnswers(questionId: String): ApiResponse<MutableList<Answer>>
    suspend fun getUserAnswers(userId: String): ApiResponse<MutableList<Answer>>
    suspend fun getClassroomAnswers(classId: String): ApiResponse<MutableList<Answer>>

    // endregion

    // region Studies

    suspend fun sendStudy(studyBody: StudyBody): ApiResponse<Unit>
    suspend fun deleteStudy(studyId: String): ApiResponse<Unit>
    suspend fun getStudies(userId: String): ApiResponse<MutableList<Study>>
    suspend fun getUserStudies(userId: String): ApiResponse<MutableList<Study>>
    suspend fun getClassroomStudies(classId: String): ApiResponse<MutableList<Study>>

    // endregion

    // region Usages

    suspend fun addUsage(usageBody: UsageData): ApiResponse<Unit>
    suspend fun getUserUsages(userId: String): ApiResponse<UsageData>

    // endregion

    // region Meetings

    suspend fun scheduleMeeting(meetingBody: MeetingBody): ApiResponse<Unit>
    suspend fun getUserMeetings(userId: String): ApiResponse<MutableList<Meeting>>

    // endregion Meetings

}