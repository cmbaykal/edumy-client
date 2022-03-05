package com.baykal.edumyclient.base.nav

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


interface ScreenController {
    // navigation
    fun onNavigated(state: ScreenState)
    fun navigateUp()
    fun popToRoute(route: String)
    fun navigateToRoute(route: String, singleTop: Boolean = false)

    // dialog
    fun setLoading(visibility: Boolean)
    fun showDialog(title: String, message: String, onDismiss: () -> Unit = {})

    val screenState: StateFlow<ScreenState>
}

class EdumyController : ScreenController {
    override val screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Idle)

    override fun onNavigated(state: ScreenState) {
        screenState.compareAndSet(state, ScreenState.Idle)
    }

    override fun popToRoute(route: String) = setState(ScreenState.PopToRoute(route))

    override fun navigateUp() = setState(ScreenState.NavigateUp())

    override fun navigateToRoute(route: String, singleTop: Boolean) = setState(ScreenState.NavigateToRoute(route, singleTop))

    override fun setLoading(visibility: Boolean) = setState(ScreenState.setLoading(visibility))

    override fun showDialog(title: String, message: String, onDismiss: () -> Unit) = setState(ScreenState.showDialog(title, message, onDismiss))

    private fun setState(state: ScreenState) {
        screenState.value = state
    }
}