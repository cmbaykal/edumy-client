package com.baykal.edumyclient.data.model.user.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class AuthTokenResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("expireTime")
    val expireTime: Date?
)