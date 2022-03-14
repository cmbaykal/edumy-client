package com.baykal.edumyclient.base.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baykal.edumyclient.EdumyApp
import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.nav.EdumyController
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel : ViewModel() {
    private val scope = viewModelScope
    var args: Bundle? = null
    val controller: EdumyController = EdumyApp.screenController



    fun setArguments(bundle: Bundle) {
        args = bundle
    }

    private fun setLoading(visibility: Boolean) {
        controller.setLoading(visibility)
    }

    private fun showError(error: String?) {
        error?.let {
            controller.showDialog("Error", error)
        }
    }

    fun navigate(route: String, singleTop: Boolean = false) {
        controller.navigateToRoute(route, singleTop)
    }

    protected fun <T> Flow<BaseResult<ApiResponse<T>>>.collectData(onSuccess: (T?) -> Unit): Job {
        setLoading(true)
        return onEach {
            delay(200)
            setLoading(false)
            if (it is BaseResult.Success) {
                if (it.response.success) {
                    onSuccess.invoke(it.response.data)
                } else {
                    showError(it.response.error)
                }
            } else if (it is BaseResult.Error) {
                showError(it.error)
            }
        }.launchIn(scope)
    }
}