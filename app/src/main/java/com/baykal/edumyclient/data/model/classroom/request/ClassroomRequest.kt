package com.baykal.edumyclient.data.model.classroom.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassroomBody(
    @SerialName("lesson")
    val lesson: String,
    @SerialName("name")
    val name: String,
    @SerialName("creatorId")
    val creatorId: String
)
