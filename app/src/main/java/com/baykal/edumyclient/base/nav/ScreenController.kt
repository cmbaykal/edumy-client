package com.baykal.edumyclient.base.nav

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


interface ScreenController {
    // navigation
    fun onStateChanged(state: ScreenState)
    fun navigateUp()
    fun popToRoute(route: String)
    fun navigateToRoute(route: String, singleTop: Boolean)

    // dialog
    fun setLoading(visibility: Boolean)
    fun showDialog(title: String, message: String, onDismiss: () -> Unit = {})

    // account
    fun login()
    fun logout()

    val screenState: StateFlow<ScreenState>
}

class EdumyController : ScreenController {
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Idle)
    override val screenState = _screenState.asStateFlow()

    override fun onStateChanged(state: ScreenState) {
        _screenState.compareAndSet(state, ScreenState.Idle)
    }

    override fun popToRoute(route: String) = setState(ScreenState.PopToRoute(route))

    override fun login() = setState(ScreenState.Login)

    override fun logout() = setState(ScreenState.Logout)

    override fun navigateUp() = setState(ScreenState.NavigateUp())

    override fun navigateToRoute(route: String, singleTop: Boolean) = setState(ScreenState.NavigateToRoute(route, singleTop))

    override fun setLoading(visibility: Boolean) = setState(ScreenState.SetLoading(visibility))

    override fun showDialog(title: String, message: String, onDismiss: () -> Unit) = setState(ScreenState.ShowDialog(title, message, onDismiss))

    private fun setState(state: ScreenState) {
        _screenState.value = state
    }
}