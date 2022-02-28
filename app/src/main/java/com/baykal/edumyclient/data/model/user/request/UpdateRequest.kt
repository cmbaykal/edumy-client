package com.baykal.edumyclient.data.model.user.request

import com.google.gson.annotations.SerializedName

data class UpdateCredentials(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("pass")
    val pass: String? = null
)