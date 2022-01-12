package com.baykal.edumyclient.base.data

import kotlinx.serialization.Serializable

@Serializable
sealed class BaseResult<T>{

    companion object{
        fun <T> success(data:T) = SuccessResult<T>(data)

        fun <T> error(message: String?) = ErrorResult<T>(message)
    }

}

@Serializable
data class SuccessResult<T>(val data:T) : BaseResult<T>()

@Serializable
data class ErrorResult<T>(val message: String?) : BaseResult<T>()