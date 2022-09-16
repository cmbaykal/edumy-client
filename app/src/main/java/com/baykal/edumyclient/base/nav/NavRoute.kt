package com.baykal.edumyclient.base.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.ui.DialogState
import com.baykal.edumyclient.ui.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface NavRoute<T : BaseViewModel> {

    val title: Int

    val route: String

    fun bottomBarVisibility(): Boolean = false

    fun topBarVisibility(): Boolean = false

    fun menuVisibility(): Boolean = true

    fun feed(): String = route

    @Composable
    fun Content(viewModel: T)

    @Composable
    fun viewModel(): T

    fun getArguments(): List<NamedNavArgument> = listOf()

    fun composable(
        builder: NavGraphBuilder,
        navHostController: NavHostController,
        mainStateFlow: MutableStateFlow<MainState>
    ) {
        builder.composable(route, getArguments()) {
            it.destination.label = stringResource(id = title)
            val viewModel = viewModel()
            val screenState by viewModel.controller.screenState.collectAsState()

            mainStateFlow.update { state ->
                state.copy(
                    pageTitle = stringResource(id = title),
                    bottomBarVisibility = bottomBarVisibility(),
                    topBarVisibility = topBarVisibility(),
                    menuVisibility = menuVisibility()
                )
            }

            LaunchedEffect(screenState) {
                updateScreenState(
                    navHostController,
                    screenState,
                    mainStateFlow,
                )
                viewModel.controller.onStateChanged(screenState)
            }

            it.arguments?.let { bundle ->
                viewModel.setArguments(bundle)
            }
            Content(viewModel)
        }
    }

    private fun updateScreenState(
        navHostController: NavHostController,
        screenState: ScreenState,
        mainState: MutableStateFlow<MainState>,
    ) {
        when (screenState) {
            is ScreenState.NavigateToRoute -> {
                if (screenState.clearHistory) {
                    mainState.update { it.copy(startRoute = screenState.route) }
                    navHostController.currentBackStackEntry?.destination?.route?.let {
                        navHostController.popBackStack(it, false)
                    }
                }
                navHostController.navigate(screenState.route)
            }
            is ScreenState.PopToRoute -> {
                navHostController.popBackStack(screenState.staticRoute, false)
            }
            is ScreenState.NavigateUp -> {
                navHostController.navigateUp()
            }
            is ScreenState.SetLoading -> {
                mainState.update { it.copy(loadingState = screenState.visibility) }
            }
            is ScreenState.ShowDialog -> {
                mainState.update { state ->
                    state.copy(
                        dialogState = DialogState(
                            title = screenState.title,
                            message = screenState.message,
                            onDismiss = {
                                mainState.update { it.copy(dialogState = null) }
                                screenState.onDismiss.invoke()
                            }
                        )
                    )
                }
            }
            else -> {} // Idle
        }
    }
}