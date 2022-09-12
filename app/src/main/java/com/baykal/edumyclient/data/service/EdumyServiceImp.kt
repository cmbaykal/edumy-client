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
import com.baykal.edumyclient.di.BASE_URL
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.http.content.*

class EdumyServiceImp(
    private val httpClient: HttpClient
) : EdumyService {
    // region User

    override suspend fun registerUser(registerCredentials: RegisterCredentials): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/user/register")
            }
            contentType(ContentType.Application.Json)
            headers {
                append("No-Auth", "true")
            }
            setBody(registerCredentials)
        }.body()
    }

    override suspend fun loginUser(loginCredentials: LoginCredentials): ApiResponse<User> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/user/login")
            }
            contentType(ContentType.Application.Json)
            headers {
                append("No-Auth", "true")
            }
            setBody(loginCredentials)
        }.body()
    }

    override suspend fun updateUser(updateCredentials: UpdateCredentials): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/user/update")
            }
            contentType(ContentType.Application.Json)
            setBody(updateCredentials)
        }.body()
    }

    override suspend fun changePassword(passwordCredentials: PasswordCredentials): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/user/changePassword")
            }
            contentType(ContentType.Application.Json)
            setBody(passwordCredentials)
        }.body()
    }

    override suspend fun getUserInformation(userId: String): ApiResponse<User> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/user/info")
                parameter("userId", userId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    // endregion

    // region Classrooms

    override suspend fun addClassroom(classroom: ClassroomBody): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/class/add")
            }
            contentType(ContentType.Application.Json)
            setBody(classroom)
        }.body()
    }

    override suspend fun assignUserToClassroom(classId: String?, userMail: String): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/class/assign")
                parameter("classId", classId)
                parameter("userMail", userMail)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getClassroomInformation(classId: String): ApiResponse<Classroom> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/class/info")
                parameter("classId", classId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getUserClassrooms(userId: String): ApiResponse<MutableList<Classroom>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/class/user")
                parameter("userId", userId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun leaveClassroom(classId: String, userMail: String?): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/class/leave")
                parameter("classId", classId)
                parameter("userMail", userMail)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun deleteClassroom(classId: String, userMail: String?): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/class/delete")
                parameter("classId", classId)
                parameter("userMail", userMail)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    // endregion

    // region Questions

    override suspend fun addQuestion(parts: List<PartData>): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/question/add")
            }
            contentType(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(parts))
        }.body()
    }

    override suspend fun deleteQuestion(questionId: String): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/question/delete")
                parameter("questionId", questionId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getQuestions(page: Int, limit: Int): ApiResponse<MutableList<Question>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/question/feed")
                parameter("page", page)
                parameter("limit", limit)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getClassroomQuestions(classId: String, page: Int, limit: Int): ApiResponse<MutableList<Question>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/question/classroom")
                parameter("classId", classId)
                parameter("page", page)
                parameter("limit", limit)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getUserQuestions(userId: String, page: Int, limit: Int): ApiResponse<MutableList<Question>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/question/user")
                parameter("userId", userId)
                parameter("page", page)
                parameter("limit", limit)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getQuestionInformation(questionId: String): ApiResponse<Question> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/question/info")
                parameter("questionId", questionId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    // endregion

    // region Answers

    override suspend fun sendAnswer(parts: List<PartData>): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/answer/add")
            }
            contentType(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(parts))
        }.body()
    }

    override suspend fun deleteAnswer(answerId: String): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/answer/delete")
                parameter("answerId", answerId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun upVoteAnswer(answerId: String, userId: String): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/answer/upvote")
                parameter("answerId", answerId)
                parameter("userId", userId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun downVoteAnswer(answerId: String, userId: String): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/answer/downvote")
                parameter("answerId", answerId)
                parameter("userId", userId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getQuestionAnswers(questionId: String): ApiResponse<MutableList<Answer>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/answer/question")
                parameter("questionId", questionId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getUserAnswers(userId: String): ApiResponse<MutableList<Answer>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/answer/user")
                parameter("userId", userId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getClassroomAnswers(classId: String): ApiResponse<MutableList<Answer>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/answer/classroom")
                parameter("classId", classId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    // endregion

    // region Studies

    override suspend fun sendStudy(studyBody: StudyBody): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/study/add")
            }
            contentType(ContentType.Application.Json)
            setBody(studyBody)
        }.body()
    }

    override suspend fun deleteStudy(studyId: String): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/study/delete")
                parameter("studyId", studyId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getStudies(userId: String): ApiResponse<MutableList<Study>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/study/feed")
                parameter("userId", userId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getUserStudies(userId: String): ApiResponse<MutableList<Study>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/study/user")
                parameter("userId", userId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getClassroomStudies(classId: String): ApiResponse<MutableList<Study>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/study/classroom")
                parameter("classId", classId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    // endregion Studies

    // region Usages

    override suspend fun addUsage(usageBody: UsageData): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/usage/add")
            }
            contentType(ContentType.Application.Json)
            setBody(usageBody)
        }.body()
    }

    override suspend fun getUserUsages(userId: String): ApiResponse<UsageData> {
        return httpClient.get {
            url {
                host = BASE_URL
                path("/usage/$userId")
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    // endregion

    // region Meetings

    override suspend fun scheduleMeeting(meetingBody: MeetingBody): ApiResponse<Unit> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/meeting/schedule")
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getUserMeetings(userId: String): ApiResponse<MutableList<Meeting>> {
        return httpClient.post {
            url {
                host = BASE_URL
                path("/meeting/user")
                parameter("userId", userId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    // endregion
}