package com.baykal.edumyclient.data.model.meeting.request

import com.google.gson.annotations.SerializedName

data class MeetingBody(
    @SerializedName("classId")
    val classId: String,
    @SerializedName("creatorId")
    val creatorId: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("date")
    var date: String
)