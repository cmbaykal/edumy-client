package com.baykal.edumyclient.data.model.user.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("role")
    val role: UserRole? = null,
    @SerializedName("mail")
    val mail: String? = null,
    @SerializedName("birth")
    val birth: Date? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("classes")
    val classes: MutableList<String>? = ArrayList()
)

enum class UserRole {
    @SerializedName("Student")
    Student,

    @SerializedName("Teacher")
    Teacher;
}
