package com.baykal.edumyclient.data.model.answer

import com.baykal.edumyclient.base.data.DateSerializer
import com.baykal.edumyclient.data.model.user.response.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Answer(
    @SerialName("id")
    val id: String? = null,
    @SerialName("questionId")
    val questionId: String? = null,
    @SerialName("user")
    var user: User? = null,
    @SerialName("description")
    var description: String? = null,
    @Serializable(with = DateSerializer::class)
    @SerialName("date")
    var date: Date? = null,
    @SerialName("image")
    var image: String? = null,
    @SerialName("video")
    var video: String? = null,
    @SerialName("upVote")
    var upVote: MutableList<String>? = null,
    @SerialName("downVote")
    var downVote: MutableList<String>? = null,
)