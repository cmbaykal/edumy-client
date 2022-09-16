package com.baykal.edumyclient.base.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baykal.edumyclient.EdumyApp
import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.nav.EdumyController
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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

    protected fun <T> Flow<ApiResponse<out T>>.collectData(
        loading: Boolean = true,
        onSuccess: (T?) -> Unit
    ): Job {
        if (loading) setLoading(true)
        return onEach {
            setLoading(true)
            if (it.error == null) {
                delay(200)
                setLoading(false)
                delay(200)
                ApiResponse.success(it.data)
                onSuccess.invoke(it.data)
            } else {
                delay(200)
                setLoading(false)
                delay(200)
                showError(it.error)
            }
        }.catch { e ->
            if (loading) setLoading(false)
            delay(200)
            showError(e.message)
        }.launchIn(scope)
    }
}