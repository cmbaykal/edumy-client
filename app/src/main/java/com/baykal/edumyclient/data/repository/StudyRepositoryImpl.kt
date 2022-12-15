package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.data.model.study.request.StudyBody
import com.baykal.edumyclient.data.service.EdumyServiceImp
import javax.inject.Inject

class StudyRepositoryImpl @Inject constructor(
    private val service: EdumyServiceImp
) : StudyRepository() {

    override fun sendStudy(body: StudyBody) = fetch {
        service.sendStudy(body)
    }

    override fun deleteStudy(studyId: String) = fetch {
        service.deleteStudy(studyId)
    }

    override fun getStudies(userId: String) = fetch {
        service.getStudies(userId)
    }

    override fun getUserStudies(userId: String) = fetch {
        service.getUserStudies(userId)
    }

    override fun getClassroomStudies(classId: String) = fetch {
        service.getClassroomStudies(classId)
    }
}