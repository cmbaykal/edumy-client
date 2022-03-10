package com.baykal.edumyclient.base.nav

import java.util.*

sealed class ScreenState {
    object Idle : ScreenState()

    data class NavigateToRoute(val route: String, val singleTop: Boolean = false, val id: String = UUID.randomUUID().toString()) : ScreenState()

    data class PopToRoute(val staticRoute: String, val id: String = UUID.randomUUID().toString()) : ScreenState()

    data class NavigateUp(val id: String = UUID.randomUUID().toString()) : ScreenState()

    data class showDialog(val title: String, val message: String, val onDismiss: () -> Unit = {}) : ScreenState()

    data class setLoading(val visibility: Boolean) : ScreenState()
}