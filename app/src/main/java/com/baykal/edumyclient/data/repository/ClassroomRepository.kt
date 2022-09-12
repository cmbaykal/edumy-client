package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.data.service.EdumyServiceImp
import javax.inject.Inject

class ClassroomRepository @Inject constructor(
    private val service: EdumyServiceImp
) : BaseRepository() {

    fun getUserClassrooms(userId: String) = fetch {
        service.getUserClassrooms(userId)
    }

    fun addClass(classroomBody: ClassroomBody) = fetch {
        service.addClassroom(classroomBody)
    }

    fun assignClass(classId: String?, userMail: String) = fetch {
        service.assignUserToClassroom(classId, userMail)
    }

    fun leaveClass(classId: String, userMail: String?) = fetch {
        service.leaveClassroom(classId, userMail)
    }

    fun deleteClass(classId: String, userMail: String?) = fetch {
        service.deleteClassroom(classId, userMail)
    }

    fun getClassInformation(classId: String) = fetch {
        service.getClassroomInformation(classId)
    }
}