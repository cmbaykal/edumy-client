package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import kotlinx.coroutines.flow.Flow

abstract class ClassroomRepository : BaseRepository() {
    abstract fun addClassroom(classroom: ClassroomBody): Flow<ApiResponse<out Unit>>
    abstract fun assignUserToClassroom(classId: String?, userMail: String): Flow<ApiResponse<out Unit>>
    abstract fun getClassroomInformation(classId: String): Flow<ApiResponse<out Classroom>>
    abstract fun getUserClassrooms(userId: String): Flow<ApiResponse<out MutableList<Classroom>>>
    abstract fun leaveClassroom(classId: String, userMail: String?): Flow<ApiResponse<out Unit>>
    abstract fun deleteClassroom(classId: String, userMail: String?): Flow<ApiResponse<out Unit>>
}