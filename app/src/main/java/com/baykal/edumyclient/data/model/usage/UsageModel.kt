package com.baykal.edumyclient.data.model.usage

import com.google.gson.annotations.SerializedName
import java.util.*

data class UsageData(
    @SerializedName("userId")
    val userId: String? = null,
    @SerializedName("usages")
    val usages: MutableList<Usage>? = ArrayList()
)

data class Usage(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("usage")
    val usage: Int? = null,
    @SerializedName("date")
    val date: Date? = null
)