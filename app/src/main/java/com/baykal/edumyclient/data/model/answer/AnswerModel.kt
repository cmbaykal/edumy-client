package com.baykal.edumyclient.data.model.answer

import com.baykal.edumyclient.data.model.user.response.User
import com.google.gson.annotations.SerializedName
import java.util.*

data class Answer(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("questionId")
    val questionId: String? = null,
    @SerializedName("user")
    var user: User? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("date")
    var date: Date? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("video")
    var video: String? = null,
    @SerializedName("upVote")
    var upVote: MutableList<String>? = null,
    @SerializedName("downVote")
    var downVote: MutableList<String>? = null,
)