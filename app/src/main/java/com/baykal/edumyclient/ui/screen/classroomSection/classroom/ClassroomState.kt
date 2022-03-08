package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import com.baykal.edumyclient.data.model.classroom.response.Classroom

data class ClassroomState(
    val classroom: Classroom? = null,
    val owner: Boolean = false
)
