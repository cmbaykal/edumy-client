package com.baykal.edumyclient.base.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<in P, R>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    protected abstract fun build(params: P): Flow<R>

    fun observe(params: P): Flow<R> = build(params).flowOn(dispatcher)

}