package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.user.response.User

data class ClassroomState(
    val user: User? = null,
    val classroom: Classroom? = null,
    val owner: Boolean = false
)
