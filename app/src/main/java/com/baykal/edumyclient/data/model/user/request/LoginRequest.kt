package com.baykal.edumyclient.data.model.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginCredentials(
    @SerialName("mail")
    val mail: String,
    @SerialName("pass")
    val pass: String
)
