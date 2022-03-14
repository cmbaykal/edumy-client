package com.baykal.edumyclient.base.nav

import java.util.*

sealed class ScreenState {
    object Idle : ScreenState()

    object Login : ScreenState()

    object Logout : ScreenState()

    data class NavigateToRoute(val route: String, val clearHistory: Boolean = false, val id: String = UUID.randomUUID().toString()) : ScreenState()

    data class PopToRoute(val staticRoute: String, val id: String = UUID.randomUUID().toString()) : ScreenState()

    data class NavigateUp(val id: String = UUID.randomUUID().toString()) : ScreenState()

    data class ShowDialog(val title: String, val message: String, val onDismiss: () -> Unit = {}) : ScreenState()

    data class SetLoading(var visibility: Boolean) : ScreenState()
}