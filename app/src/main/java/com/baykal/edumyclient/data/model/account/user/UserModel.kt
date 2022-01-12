package com.baykal.edumyclient.data.model.account.user

import com.baykal.edumyclient.base.data.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserModel(
    @SerialName("id")
    val id: String? = null,
    @SerialName("role")
    val role: String? = null,
    @SerialName("mail")
    val mail: String? = null,
    @SerialName("birth")
    @Serializable(with = DateSerializer::class)
    val birth: Date? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("bio")
    val bio: String? = null
)
