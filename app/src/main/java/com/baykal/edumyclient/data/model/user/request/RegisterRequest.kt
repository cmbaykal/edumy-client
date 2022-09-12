package com.baykal.edumyclient.data.model.user.request

import com.baykal.edumyclient.data.model.user.response.UserRole
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterCredentials(
    @SerialName("role")
    val role: UserRole? = null,
    @SerialName("mail")
    val mail: String? = null,
    @SerialName("birth")
    val birth: String? = null,
    @SerialName("pass")
    val pass: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("bio")
    val bio: String? = null,
)