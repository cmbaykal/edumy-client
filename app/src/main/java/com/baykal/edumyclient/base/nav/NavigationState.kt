package com.baykal.edumyclient.base.nav

import java.util.*

sealed class NavigationState {
    object Idle : NavigationState()

    data class NavigateToRoute(val route: String, val singleTop: Boolean = false, val id: String = UUID.randomUUID().toString()) :
        NavigationState()

    data class PopToRoute(val staticRoute: String, val id: String = UUID.randomUUID().toString()) :
        NavigationState()

    data class NavigateUp(val id: String = UUID.randomUUID().toString()) : NavigationState()
}