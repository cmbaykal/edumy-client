package com.baykal.edumyclient.data.model.meeting.response

import android.os.Parcelable
import com.baykal.edumyclient.base.data.DateSerializer
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.user.response.User
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Meeting(
    @SerialName("id")
    val id: String,
    @SerialName("user")
    val user: User? = null,
    @SerialName("classroom")
    val classroom: Classroom? = null,
    @SerialName("description")
    var description: String? = null,
    @SerialName("duration")
    val duration: Int,
    @Serializable(with = DateSerializer::class)
    @SerialName("date")
    var date: Date? = null,
)