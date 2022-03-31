package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.user.response.UserRole

data class ClassroomsState(
    val userRole: UserRole? = null,
    val classrooms: MutableList<Classroom>? = null,
    val searchText: String = ""
)