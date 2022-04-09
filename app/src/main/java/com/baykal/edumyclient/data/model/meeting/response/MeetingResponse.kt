package com.baykal.edumyclient.data.model.meeting.response

import android.os.Parcelable
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.user.response.User
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Meeting(
    @SerializedName("id")
    val id: String,
    @SerializedName("user")
    val user: User? = null,
    @SerializedName("classroom")
    val classroom: Classroom? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("duration")
    val duration:Int,
    @SerializedName("date")
    var date: Date? = null,
):Parcelable