package com.baykal.edumyclient.base.nav

import androidx.compose.runtime.*
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.baykal.edumyclient.ui.MainState

interface NavRoute<T : RouteNavigator> {

    val title: String

    val route: String

    fun bottomBarVisibility(): Boolean = false

    fun topBarVisibility(): Boolean = false

    @Composable
    fun Content(viewModel: T)

    @Composable
    fun viewModel(): T

    fun getArguments(): List<NamedNavArgument> = listOf()

    fun composable(
        builder: NavGraphBuilder,
        navHostController: NavHostController,
        mainState: MutableState<MainState>
    ) {
        builder.composable(route, getArguments()) {
            val viewModel = viewModel()
            val viewStateAsState by viewModel.navigationState.collectAsState()

            LaunchedEffect(viewStateAsState) {
                mainState.value = MainState(
                    title = title,
                    bottomBarVisibility = bottomBarVisibility(),
                    topBarVisibility = topBarVisibility()
                )
                updateNavigationState(navHostController, viewStateAsState, viewModel::onNavigated)
            }

            Content(viewModel)
        }
    }

    private fun updateNavigationState(
        navHostController: NavHostController,
        navigationState: NavigationState,
        onNavigated: (navState: NavigationState) -> Unit,
    ) {
        when (navigationState) {
            is NavigationState.NavigateToRoute -> {
                navHostController.navigate(navigationState.route) {
                    if (navigationState.singleTop) {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                inclusive = true
                            }
                        }
                    }
                }
                onNavigated(navigationState)
            }
            is NavigationState.PopToRoute -> {
                navHostController.popBackStack(navigationState.staticRoute, false)
                onNavigated(navigationState)
            }
            is NavigationState.NavigateUp -> {
                navHostController.navigateUp()
            }
            is NavigationState.Idle -> {
            }
        }
    }
}