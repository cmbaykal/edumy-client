package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.data.service.EdumyServiceImp
import javax.inject.Inject

class ClassroomRepositoryImpl @Inject constructor(
    private val service: EdumyServiceImp
) : ClassroomRepository() {

    override fun getUserClassrooms(userId: String) = fetch {
        service.getUserClassrooms(userId)
    }

    override fun addClassroom(classroom: ClassroomBody) = fetch {
        service.addClassroom(classroom)
    }

    override fun assignUserToClassroom(classId: String?, userMail: String) = fetch {
        service.assignUserToClassroom(classId, userMail)
    }

    override fun leaveClassroom(classId: String, userMail: String?) = fetch {
        service.leaveClassroom(classId, userMail)
    }

    override fun deleteClassroom(classId: String, userMail: String?) = fetch {
        service.deleteClassroom(classId, userMail)
    }

    override fun getClassroomInformation(classId: String) = fetch {
        service.getClassroomInformation(classId)
    }
}