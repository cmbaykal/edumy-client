package com.baykal.edumyclient.data.model.user.request

import com.baykal.edumyclient.data.model.user.response.UserRole
import com.google.gson.annotations.SerializedName

data class RegisterCredentials(
    @SerializedName("role")
    val role: UserRole? = null,
    @SerializedName("mail")
    val mail: String? = null,
    @SerializedName("birth")
    val birth: String? = null,
    @SerializedName("pass")
    val pass: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
)