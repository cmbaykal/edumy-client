package com.baykal.edumyclient.base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.nav.RouteNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    protected val scope = viewModelScope

    @Inject
    lateinit var navigator: RouteNavigator

    private fun sendError(error: String?) {

    }

    protected fun <T> Flow<BaseResult<T>>.collect(onSuccess: (T?) -> Unit) = onEach {
        if (it is BaseResult.Success) {
            onSuccess.invoke(it.data)
        } else if (it is BaseResult.Error) {
            sendError(it.error)
        }
    }.launchIn(scope)

}