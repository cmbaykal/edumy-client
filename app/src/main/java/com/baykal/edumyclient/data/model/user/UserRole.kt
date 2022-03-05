package com.baykal.edumyclient.data.model.user

import com.google.gson.annotations.SerializedName

enum class UserRole {
    @SerializedName("s")
    Student,

    @SerializedName("t")
    Teacher;
}