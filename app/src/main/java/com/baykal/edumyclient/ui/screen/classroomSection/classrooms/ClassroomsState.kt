package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import com.baykal.edumyclient.data.model.classroom.response.Classroom

data class ClassroomsState(
    val classrooms: MutableList<Classroom> = mutableListOf(),
    val searchText: String = ""
)