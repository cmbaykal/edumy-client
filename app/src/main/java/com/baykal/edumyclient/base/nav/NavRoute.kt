package com.baykal.edumyclient.base.nav

import androidx.compose.runtime.*
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.ui.DialogState
import com.baykal.edumyclient.ui.MainState

interface NavRoute<T : BaseViewModel> {

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
            val viewStateAsState by viewModel.controller.screenState.collectAsState()

            LaunchedEffect(viewStateAsState) {
                mainState.value = MainState(
                    title = title,
                    bottomBarVisibility = bottomBarVisibility(),
                    topBarVisibility = topBarVisibility()
                )
                updateNavigationState(
                    navHostController,
                    viewStateAsState,
                    mainState,
                    viewModel.controller::onNavigated
                )
            }

            Content(viewModel)
        }
    }

    private fun updateNavigationState(
        navHostController: NavHostController,
        screenState: ScreenState,
        mainState: MutableState<MainState>,
        onNavigated: (screenState: ScreenState) -> Unit,
    ) {
        when (screenState) {
            is ScreenState.NavigateToRoute -> {
                navHostController.navigate(screenState.route) {
                    if (screenState.singleTop) {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                inclusive = true
                            }
                        }
                    }
                }
                onNavigated(screenState)
            }
            is ScreenState.PopToRoute -> {
                navHostController.popBackStack(screenState.staticRoute, false)
                onNavigated(screenState)
            }
            is ScreenState.NavigateUp -> {
                navHostController.navigateUp()
            }
            is ScreenState.setLoading -> {
                mainState.value = mainState.value.copy(loading = screenState.visibility)
            }
            is ScreenState.showDialog -> {
                mainState.value = mainState.value.copy(
                    dialog = DialogState(
                        title = screenState.title,
                        message = screenState.message,
                        onDismiss = {
                            mainState.value = mainState.value.copy(dialog = null)
                            screenState.onDismiss
                        }
                    )
                )
            }
            else -> {} // Idle
        }
    }
}