package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.study.request.StudyBody
import com.baykal.edumyclient.data.model.study.response.Study
import kotlinx.coroutines.flow.Flow

abstract class StudyRepository : BaseRepository() {
    abstract fun sendStudy(studyBody: StudyBody): Flow<ApiResponse<out Unit>>
    abstract fun deleteStudy(studyId: String): Flow<ApiResponse<out Unit>>
    abstract fun getStudies(userId: String): Flow<ApiResponse<out MutableList<Study>>>
    abstract fun getUserStudies(userId: String): Flow<ApiResponse<out MutableList<Study>>>
    abstract fun getClassroomStudies(classId: String): Flow<ApiResponse<out MutableList<Study>>>
}