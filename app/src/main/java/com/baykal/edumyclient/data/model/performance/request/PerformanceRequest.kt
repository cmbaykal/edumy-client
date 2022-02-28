package com.baykal.edumyclient.data.model.performance.request

import com.google.gson.annotations.SerializedName
import java.util.*

data class PerformanceBody(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("lesson")
    val lesson: String,
    @SerializedName("correctA")
    val correctA: Int,
    @SerializedName("wrongA")
    val wrongA: Int,
    @SerializedName("date")
    val date: Date
)