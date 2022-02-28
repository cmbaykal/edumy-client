package com.baykal.edumyclient.data.model.user.request

import com.google.gson.annotations.SerializedName

data class LoginCredentials(
    @SerializedName("mail")
    val mail: String,
    @SerializedName("pass")
    val pass: String
)
