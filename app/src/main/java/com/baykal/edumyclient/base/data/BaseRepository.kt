package com.baykal.edumyclient.base.data

import kotlinx.coroutines.flow.flow

open class BaseRepository {

    protected fun <T> fetch(apiCall: suspend () -> BaseResult<T>) = flow {
        emit(BaseResult.Loading)
        emit(apiCall())
    }

}