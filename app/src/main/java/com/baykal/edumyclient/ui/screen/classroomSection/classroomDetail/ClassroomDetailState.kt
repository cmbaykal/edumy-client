package com.baykal.edumyclient.ui.screen.classroomSection.classroomDetail

import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.user.response.User

data class ClassroomDetailState(
    val user: User? = null,
    val classroom: Classroom? = null,
    val owner: Boolean = false
)
