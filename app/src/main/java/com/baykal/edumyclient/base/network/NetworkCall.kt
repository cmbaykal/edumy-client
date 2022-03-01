package com.baykal.edumyclient.base.network

import com.baykal.edumyclient.base.data.BaseResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkCall<R>(
    private val delegate: Call<R>
) : Call<BaseResult<R>> {

    override fun enqueue(callback: Callback<BaseResult<R>>) {
        delegate.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                val result = when (response.code()) {
                    in 200 until 300 -> {
                        response.body()?.let {
                            BaseResult.Success(it)
                        } ?: run {
                            BaseResult.Error("Null Response")
                        }
                    }
                    else -> {
                        BaseResult.Error(response.message())
                    }
                }
                callback.onResponse(this@NetworkCall, Response.success(result))
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                val result = BaseResult.Error(t.message)
                callback.onResponse(this@NetworkCall, Response.success(result))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted
    override fun clone() = NetworkCall(delegate.clone())
    override fun isCanceled() = delegate.isCanceled
    override fun cancel() = delegate.cancel()
    override fun execute(): Response<BaseResult<R>> {
        throw UnsupportedOperationException("NetworkCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
}
