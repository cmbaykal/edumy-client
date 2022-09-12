package com.baykal.edumyclient.data.model.user.response

import com.baykal.edumyclient.base.data.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class User(
    @SerialName("id")
    val id: String? = null,
    @SerialName("role")
    val role: UserRole? = null,
    @SerialName("mail")
    val mail: String? = null,
    @Serializable(with = DateSerializer::class)
    @SerialName("birth")
    val birth: Date? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("classes")
    val classes: MutableList<String>? = ArrayList()
)

@Serializable
enum class UserRole {
    @SerialName("Student")
    Student,

    @SerialName("Teacher")
    Teacher;
}
