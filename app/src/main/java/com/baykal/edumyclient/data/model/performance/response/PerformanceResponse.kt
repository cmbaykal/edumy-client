package com.baykal.edumyclient.data.model.performance.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class Performance(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("userId")
    val userId: String? = null,
    @SerializedName("lesson")
    val lesson: String? = null,
    @SerializedName("correctA")
    val correctA: Int? = null,
    @SerializedName("wrongA")
    val wrongA: Int? = null,
    @SerializedName("date")
    val date: Date? = null
)
