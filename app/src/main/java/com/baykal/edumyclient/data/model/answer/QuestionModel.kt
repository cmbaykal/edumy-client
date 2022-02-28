package com.baykal.edumyclient.data.model.answer

import com.google.gson.annotations.SerializedName
import java.util.*

data class Answer(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("questionId")
    var questionId: String? = null,
    @SerializedName("userId")
    var userId: String? = null,
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("date")
    var date: Date? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("video")
    var video: String? = null
)