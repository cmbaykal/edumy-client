package com.baykal.edumyclient.data.model.usage

import com.baykal.edumyclient.base.data.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UsageData(
    @SerialName("userId")
    val userId: String? = null,
    @SerialName("usages")
    val usages: MutableList<Usage>? = ArrayList()
)

@Serializable
data class Usage(
    @SerialName("name")
    val name: String? = null,
    @SerialName("usage")
    val usage: Int? = null,
    @Serializable(with = DateSerializer::class)
    @SerialName("date")
    val date: Date? = null
)