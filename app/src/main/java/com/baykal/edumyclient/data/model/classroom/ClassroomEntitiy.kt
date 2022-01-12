package com.baykal.edumyclient.data.model.classroom

import com.baykal.edumyclient.data.model.account.user.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassroomEntity(
    @SerialName("id")
    val id: String,
    @SerialName("lesson")
    val lesson: String,
    @SerialName("name")
    val name: String,
    @SerialName("users")
    val users: MutableList<UserModel>? = ArrayList()
)