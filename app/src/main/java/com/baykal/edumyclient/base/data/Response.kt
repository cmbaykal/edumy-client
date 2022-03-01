package com.baykal.edumyclient.base.data

import com.google.gson.annotations.SerializedName


open class BaseResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("error") val error: String? = null
)

data class ApiResponse<out T>(
    @SerializedName("data") val data: T
) : BaseResponse()
