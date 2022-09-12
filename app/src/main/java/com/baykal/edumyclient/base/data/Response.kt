package com.baykal.edumyclient.base.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse(
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

        fun <T> success(data: T? = null) = ApiResponse(data)

        fun error(message: String? = null) = BaseResponse(
            error = message ?: "Oops, an error occurred. Please try again.",
        )
    }
}