package com.baykal.edumyclient.data.model.study.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyBody(
    @SerialName("userId")
    val userId: String,
    @SerialName("lesson")
    val lesson: String,
    @SerialName("correctA")
    val correctA: String,
    @SerialName("wrongA")
    val wrongA: String,
    @SerialName("emptyQ")
    val emptyQ: String,
    @SerialName("date")
    val date: String
)