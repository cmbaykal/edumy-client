package com.baykal.edumyclient.data.model.classroom.response

import android.os.Parcelable
import com.baykal.edumyclient.data.model.user.response.User
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
):Parcelable