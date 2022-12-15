package com.baykal.edumyclient.base.data

import kotlinx.coroutines.flow.flow

open class BaseRepository {

    protected fun <T> fetch(apiCall: suspend () -> ApiResponse<T>) = flow {
        emit(ApiResponse.loading())
        emit(apiCall())
    }
}