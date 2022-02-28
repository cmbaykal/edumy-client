package com.baykal.edumyclient.base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    protected fun <T> Flow<BaseResult<T>>.collect(onSuccess: (T?) -> Unit): Job {
        controller.setLoading(true)
        return onEach {
            if (it is BaseResult.Success) {
                controller.setLoading(false)
                onSuccess.invoke(it.data)
            } else if (it is BaseResult.Error) {
                controller.setLoading(false)
                showError(it.error)
            }
        }.launchIn(scope)
    }
}