package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.study.request.StudyBody
import com.baykal.edumyclient.data.service.EdumyServiceImp
import javax.inject.Inject

class StudyRepository @Inject constructor(
    private val service: EdumyServiceImp
) : BaseRepository() {

    fun sendStudy(body: StudyBody) = fetch {
        service.sendStudy(body)
    }

    fun getStudies(userId: String) = fetch {
        service.getStudies(userId)
    }

    fun getUserStudies(userId: String) = fetch {
        service.getUserStudies(userId)
    }

    fun getClassroomStudies(classId: String) = fetch {
        service.getClassroomStudies(classId)
    }
}