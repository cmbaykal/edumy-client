package com.baykal.edumyclient.data.model.user.response

import com.baykal.edumyclient.base.data.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class AuthTokenResponse(
    @SerialName("token")
    val token: String,
    @Serializable(with = DateSerializer::class)
    @SerialName("expireTime")
    val expireTime: Date?
)