package com.baykal.edumyclient.data.model.classroom.request

import com.baykal.edumyclient.data.model.user.response.User
import com.google.gson.annotations.SerializedName

data class ClassroomBody(
    @SerializedName("lesson")
    val lesson: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("users")
    val users: MutableList<User>? = ArrayList()
)
