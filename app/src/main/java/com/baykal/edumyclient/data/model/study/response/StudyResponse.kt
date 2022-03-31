package com.baykal.edumyclient.data.model.study.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class Study(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("lesson")
    val lesson: String? = null,
    @SerializedName("correctA")
    val correctA: String? = null,
    @SerializedName("wrongA")
    val wrongA: String? = null,
    @SerializedName("emptyA")
    val emptyA: String? = null,
    @SerializedName("date")
    val date: Date? = null
)
