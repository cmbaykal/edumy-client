package com.baykal.edumyclient.base.data


sealed class BaseResult<out T> {
    data class Success<out T : Any>(val data: T) : BaseResult<T>()
    data class Error(val error: String?) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}