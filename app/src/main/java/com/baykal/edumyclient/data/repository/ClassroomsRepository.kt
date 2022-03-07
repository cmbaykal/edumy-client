package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.data.service.EdumyService
import javax.inject.Inject

class ClassroomsRepository @Inject constructor(
    private val service: EdumyService
) : BaseRepository() {

    fun getUserClassrooms(userId: String) = fetch {
        service.getUserClassrooms(userId)
    }

    fun addClass(classroomBody: ClassroomBody) = fetch {
        service.addClassroom(classroomBody)
    }

    fun assignClass(classId: String, userId: String) = fetch {
        service.assignUserToClassroom(classId, userId)
    }

    fun leaveClass(classId: String, userId: String) = fetch {
        service.leaveClassroom(classId, userId)
    }

    fun deleteClass(classId: String) = fetch {
        service.deleteClassroom(classId)
    }

    fun getClassInformation(classId: String) = fetch {
        service.getClassroomInformation(classId)
    }

}