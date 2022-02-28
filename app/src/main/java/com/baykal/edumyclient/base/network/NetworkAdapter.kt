package com.baykal.edumyclient.base.network

import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.data.BaseResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, Call<BaseResult<R>>> {

    override fun responseType() = responseType
    override fun adapt(call: Call<R>)= NetworkCall(call)

}