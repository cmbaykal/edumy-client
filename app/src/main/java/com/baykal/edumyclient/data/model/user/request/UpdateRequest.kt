package com.baykal.edumyclient.data.model.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateCredentials(
    @SerialName("userId")
    val userId: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("bio")
    val bio: String? = null,
)

@Serializable
data class PasswordCredentials(
    @SerialName("userId")
    val userId: String,
    @SerialName("oldPass")
    val oldPass: String,
    @SerialName("newPass")
    val newPass: String,
)