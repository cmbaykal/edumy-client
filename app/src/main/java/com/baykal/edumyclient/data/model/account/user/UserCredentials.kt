package com.baykal.edumyclient.data.model.account.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginCredentials(
    @SerialName("mail")
    val mail: String,
    @SerialName("pass")
    val pass: String
)

@Serializable
data class RegisterCredentials(
    @SerialName("role")
    val role: String,
    @SerialName("mail")
    val mail: String,
    @SerialName("pass")
    val pass: String,
    @SerialName("name")
    val name: String,
    @SerialName("birth")
    val birth: String,
)
