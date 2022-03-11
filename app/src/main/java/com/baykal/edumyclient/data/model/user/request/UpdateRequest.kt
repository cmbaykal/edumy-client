package com.baykal.edumyclient.data.model.user.request

import com.google.gson.annotations.SerializedName

data class UpdateCredentials(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
)

data class PasswordCredentials(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("oldPass")
    val oldPass: String,
    @SerializedName("newPass")
    val newPass: String,
)