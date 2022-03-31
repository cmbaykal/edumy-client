package com.baykal.edumyclient.data.model.study.request

import com.google.gson.annotations.SerializedName

data class StudyBody(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("lesson")
    val lesson: String,
    @SerializedName("correctA")
    val correctA: String,
    @SerializedName("wrongA")
    val wrongA: String,
    @SerializedName("emptyA")
    val emptyA: String,
    @SerializedName("date")
    val date: String
)