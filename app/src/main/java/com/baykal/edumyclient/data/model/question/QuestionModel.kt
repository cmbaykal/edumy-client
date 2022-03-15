package com.baykal.edumyclient.data.model.question

import com.google.gson.annotations.SerializedName
import java.util.*

data class Question(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("userId")
    var userId: String? = null,
    @SerializedName("lesson")
    var lesson: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("date")
    var date: Date? = null,
    @SerializedName("image")
    var image: String? = null
)