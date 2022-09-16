package com.baykal.edumyclient.data.model.classroom.response

import android.os.Parcelable
import com.baykal.edumyclient.data.model.user.response.User
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Classroom(
    @SerialName("id")
    val id: String? = null,
    @SerialName("lesson")
    val lesson: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("creatorId")
    val creatorId: String? = null,
    @SerialName("users")
    val users: MutableList<User>? = ArrayList()
)