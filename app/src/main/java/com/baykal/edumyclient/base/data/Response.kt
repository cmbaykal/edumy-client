package com.baykal.edumyclient.base.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse(
    @SerialName("success")
    var success: Boolean? = null,

    @SerialName("error")
    var error: String? = null
)

@Serializable
data class ApiResponse<T>(
    @SerialName("data")
    var data: T? = null
) : BaseResponse() {
    companion object {
        fun loading() = ApiResponse(null)

        fun <T> success(data: T? = null) = ApiResponse(data).apply {
            success = true
        }

        fun error(message: String? = null) = BaseResponse(
            success = false,
            error = message ?: "Oops, an error occurred. Please try again.",
        )
    }
}