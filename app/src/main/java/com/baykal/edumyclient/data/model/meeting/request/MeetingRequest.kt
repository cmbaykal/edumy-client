package com.baykal.edumyclient.data.model.meeting.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingBody(
    @SerialName("classId")
    val classId: String,
    @SerialName("creatorId")
    val creatorId: String,
    @SerialName("description")
    var description: String,
    @SerialName("duration")
    val duration: Int,
    @SerialName("date")
    var date: String
)