package com.baykal.edumyclient.data.model.question

import com.baykal.edumyclient.base.data.DateSerializer
import com.baykal.edumyclient.data.model.user.response.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Question(
    @SerialName("id")
    val id: String? = null,
    @SerialName("userId")
    var userId: String? = null,
    @SerialName("user")
    var user: User? = null,
    @SerialName("lesson")
    var lesson: String? = null,
    @SerialName("description")
    var description: String? = null,
    @Serializable(with = DateSerializer::class)
    @SerialName("date")
    var date: Date? = null,
    @SerialName("image")
    var image: String? = null
)