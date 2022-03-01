package com.baykal.edumyclient.base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.nav.EdumyController
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    protected val scope = viewModelScope

    @Inject
    lateinit var controller: EdumyController

    protected fun setLoading(visibility: Boolean) {
        controller.setLoading(visibility)
    }

    protected fun showError(error: String?) {
        error?.let {
            controller.showDialog("Error", error)
        }
    }

    protected fun <T> Flow<BaseResult<ApiResponse<T>>>.collect(onSuccess: (ApiResponse<T>?) -> Unit): Job {
        setLoading(true)
        return onEach {
            setLoading(false)
            if (it is BaseResult.Success) {
                if (it.response.success) {
                    onSuccess.invoke(it.response)
                } else {
                    showError(it.response.error)
                }
            } else if (it is BaseResult.Error) {
                showError(it.error)
            }
        }.launchIn(scope)
    }
}