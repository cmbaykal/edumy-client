package com.baykal.edumyclient.base.nav

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateUp()
    fun popToRoute(route: String)
    fun navigateToRoute(route: String, singleTop: Boolean = false)

    val navigationState: StateFlow<NavigationState>
}

class EdumyNavigator : RouteNavigator {

    override val navigationState: MutableStateFlow<NavigationState> = MutableStateFlow(NavigationState.Idle)

    override fun onNavigated(state: NavigationState) {
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun popToRoute(route: String) = navigate(NavigationState.PopToRoute(route))

    override fun navigateUp() = navigate(NavigationState.NavigateUp())

    override fun navigateToRoute(route: String, singleTop: Boolean) = navigate(NavigationState.NavigateToRoute(route, singleTop))

    @VisibleForTesting
    fun navigate(state: NavigationState) {
        navigationState.value = state
    }
}