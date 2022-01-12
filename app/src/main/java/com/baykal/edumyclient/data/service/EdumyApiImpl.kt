package com.baykal.edumyclient.data.service

import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.ErrorResult
import com.baykal.edumyclient.data.model.account.RegisterResponse
import com.baykal.edumyclient.data.model.classroom.ClassroomEntity
import com.baykal.edumyclient.data.model.account.user.LoginCredentials
import com.baykal.edumyclient.data.model.account.user.RegisterCredentials
import com.baykal.edumyclient.data.model.account.user.UserModel
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import java.lang.Exception

class EdumyApiImpl(
    private val client: HttpClient
) : EdumyApi {

    override suspend fun RegisterUser(credentials: RegisterCredentials): BaseResult<RegisterResponse> {
        return try {
            BaseResult.success(
                client.post {
                    url(Routes.registerUser)
                    contentType(ContentType.Application.Json)
                    body = credentials
                }
            )
        } catch (e: Exception) {
            BaseResult.error(e.message)
        }
    }

    override suspend fun LoginUser(credentials: LoginCredentials): BaseResult<UserModel> {
        return try {
            BaseResult.success(
                client.post {
                    url(Routes.loginUser)
                    contentType(ContentType.Application.Json)
                    body = credentials
                })
        } catch (e: Exception) {
            BaseResult.error(e.message)
        }
    }

    override suspend fun UpdateUser(userModel: UserModel): BaseResult<Unit> {
        return try {
            BaseResult.success(
                client.post {
                    url(Routes.updateUser)
                    contentType(ContentType.Application.Json)
                    body = userModel
                })
        } catch (e: Exception) {
            BaseResult.error(e.message)
        }
    }

    override suspend fun UserInfo(userId: String): BaseResult<UserModel> {
        return try {
            BaseResult.success(
                client.get {
                    url(Routes.userInfo)
                    contentType(ContentType.Application.Json)
                    formData {
                        parameter("userId", userId)
                    }
                })
        } catch (e: Exception) {
            BaseResult.error(e.message)
        }
    }

    override suspend fun AddClass(classroom: ClassroomEntity): BaseResult<Unit> {
        return try {
            BaseResult.success(
                client.post {
                    url(Routes.addClass)
                    contentType(ContentType.Application.Json)
                    body = classroom
                })
        } catch (e: Exception) {
            BaseResult.error(e.message)
        }
    }

    override suspend fun ClassroomInfo(classId: String): BaseResult<ClassroomEntity> {
        return try {
            BaseResult.success(
                client.get {
                    url(Routes.classroomInfo)
                    contentType(ContentType.Application.Json)
                    formData {
                        parameter("classId", classId)
                    }
                })
        } catch (e: Exception) {
            BaseResult.error(e.message)
        }
    }

    override suspend fun AssignUser(classId: String, userId: String): BaseResult<Unit> {
        return try {
            BaseResult.success(
                client.post {
                    url(Routes.assignUser)
                    contentType(ContentType.Application.Json)
                    formData {
                        parameter("classId", classId)
                        parameter("userId", userId)
                    }
                })
        } catch (e: Exception) {
            BaseResult.error(e.message)
        }
    }

    override suspend fun LeaveClassroom(classId: String, userId: String): BaseResult<Unit> {
        return try {
            BaseResult.success(
                client.post {
                    url(Routes.leaveUser)
                    contentType(ContentType.Application.Json)
                    formData {
                        parameter("classId", classId)
                        parameter("userId", userId)
                    }
                })
        } catch (e: Exception) {
            BaseResult.error(e.message)
        }
    }

}