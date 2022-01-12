package com.baykal.edumyclient.data.service

import com.baykal.edumyclient.base.data.BaseResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.data.model.account.RegisterResponse
import com.baykal.edumyclient.data.model.classroom.ClassroomEntity
import com.baykal.edumyclient.data.model.account.user.LoginCredentials
import com.baykal.edumyclient.data.model.account.user.RegisterCredentials
import com.baykal.edumyclient.data.model.account.user.UserModel


interface EdumyApi {

    suspend fun RegisterUser(credentials: RegisterCredentials): BaseResult<RegisterResponse>
    suspend fun LoginUser(credentials: LoginCredentials): BaseResult<UserModel>
    suspend fun UpdateUser(userModel: UserModel): BaseResult<Unit>
    suspend fun UserInfo(userId: String): BaseResult<UserModel>

    suspend fun AddClass(classroom: ClassroomEntity): BaseResult<Unit>
    suspend fun ClassroomInfo(classId: String): BaseResult<ClassroomEntity>
    suspend fun AssignUser(userId: String, classId: String): BaseResult<Unit>
    suspend fun LeaveClassroom(userId: String, classId: String): BaseResult<Unit>

//    suspend fun AddQuestion(): BaseResponse<Nothing>
//    suspend fun DeleteQuestion(): BaseResponse<Nothing>
//    suspend fun QuestionInfo(): BaseResponse<Nothing>
//    suspend fun ClassroomQuestions(): BaseResponse<Nothing>
//    suspend fun UserQuestions(): BaseResponse<Nothing>
//
//    suspend fun AddAnswer(): BaseResponse<Nothing>
//    suspend fun DeleteAnswer(): BaseResponse<Nothing>
//    suspend fun QuestionAnswers(): BaseResponse<Nothing>
//    suspend fun UserAnswers(): BaseResponse<Nothing>
//
//    suspend fun AddPerformance(): BaseResponse<Nothing>
//    suspend fun UserPerformances(): BaseResponse<Nothing>
//
//    suspend fun AddAppUsage(): BaseResponse<Nothing>
//    suspend fun UserUsages(): BaseResponse<Nothing>

    // TODO : Meeting API

}
