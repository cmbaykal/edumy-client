package com.baykal.edumyclient.base.data


sealed class BaseResult<out T> {
    data class Success<out T : Any>(val response: T) : BaseResult<T>()
    data class Error(val error: String?) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}