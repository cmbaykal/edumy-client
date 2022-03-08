package com.baykal.edumyclient.data.model.classroom.response

import com.baykal.edumyclient.data.model.user.response.User
import com.google.gson.annotations.SerializedName

data class Classroom(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("lesson")
    val lesson: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("creatorId")
    val creatorId: String? = null,
    @SerializedName("users")
    val users: MutableList<User>? = ArrayList()
)