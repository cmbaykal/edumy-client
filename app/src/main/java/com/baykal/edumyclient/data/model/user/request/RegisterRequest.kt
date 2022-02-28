package com.baykal.edumyclient.data.model.user.request

import com.google.gson.annotations.SerializedName
import java.util.*

data class RegisterCredentials(
    @SerializedName("role")
    val role: String? = null,
    @SerializedName("mail")
    val mail: String? = null,
    @SerializedName("birth")
    val birth: Date? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
)