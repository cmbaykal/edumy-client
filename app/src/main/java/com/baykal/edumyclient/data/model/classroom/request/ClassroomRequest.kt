package com.baykal.edumyclient.data.model.classroom.request

import com.google.gson.annotations.SerializedName

data class ClassroomBody(
    @SerializedName("lesson")
    val lesson: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("creatorId")
    val creatorId: String
)
