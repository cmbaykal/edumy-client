package com.baykal.edumyclient.data.model.study.response

import com.baykal.edumyclient.base.data.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Study(
    @SerialName("id")
    val id: String? = null,
    @SerialName("lesson")
    val lesson: String? = null,
    @SerialName("correctA")
    val correctA: String? = null,
    @SerialName("wrongA")
    val wrongA: String? = null,
    @SerialName("emptyQ")
    val emptyQ: String? = null,
    @Serializable(with = DateSerializer::class)
    @SerialName("date")
    val date: Date? = null
)
