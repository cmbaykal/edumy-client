package com.baykal.edumyclient.base.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.ui.DialogState
import com.baykal.edumyclient.ui.MainState
import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

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
        mainStateFlow: MutableStateFlow<MainState>
    ) {
        builder.composable(route, getArguments()) {
            val viewModel = viewModel()
            val screenState by viewModel.controller.screenState.collectAsState()

            mainStateFlow.update { state ->
                state.copy(
                    title = title,
                    bottomBarVisibility = bottomBarVisibility(),
                    topBarVisibility = topBarVisibility()
                )
            }

            LaunchedEffect(screenState) {
                updateNavigationState(
                    navHostController,
                    screenState,
                    mainStateFlow,
                    viewModel.controller::onNavigated
                )
                Log.d("EdumyTest", "screen state : $screenState")
            }

            it.arguments?.let { bundle ->
                viewModel.setArguments(bundle)
            }
            Content(viewModel)
        }
    }

    private fun updateNavigationState(
        navHostController: NavHostController,
        screenState: ScreenState,
        mainState: MutableStateFlow<MainState>,
        onNavigated: (screenState: ScreenState) -> Unit,
    ) {
        when (screenState) {
            is ScreenState.NavigateToRoute -> {
                if (screenState.clearHistory) {
                    mainState.update { it.copy(startRoute = screenState.route) }
                }
                navHostController.navigate(screenState.route)
                onNavigated(screenState)
            }
            is ScreenState.PopToRoute -> {
                navHostController.popBackStack(screenState.staticRoute, false)
                onNavigated(screenState)
            }
            is ScreenState.NavigateUp -> {
                navHostController.navigateUp()
                onNavigated(screenState)
            }
            is ScreenState.setLoading -> {
                mainState.update { it.copy(loading = screenState.visibility) }
            }
            is ScreenState.showDialog -> {
                mainState.update { state ->
                    state.copy(
                        dialog = DialogState(
                            title = screenState.title,
                            message = screenState.message,
                            onDismiss = {
                                mainState.update { it.copy(dialog = null) }
                                screenState.onDismiss.invoke()
                            }
                        )
                    )
                }
            }
            is ScreenState.login -> {
                mainState.update { it.copy(loggedIn = true, startRoute = ClassroomsRoute.route) }
                navHostController.navigate(ClassroomsRoute.route)
                onNavigated(screenState)
            }
            is ScreenState.logout -> {
                mainState.update { it.copy(loggedIn = false, startRoute = LoginRoute.route) }
                navHostController.navigate(LoginRoute.route)
                onNavigated(screenState)
            }
            else -> {} // Idle
        }
    }
}