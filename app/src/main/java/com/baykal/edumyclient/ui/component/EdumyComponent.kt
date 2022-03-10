package com.baykal.edumyclient.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.baykal.edumyclient.base.component.EdumyBottomBar
import com.baykal.edumyclient.base.component.EdumyToolbar
import com.baykal.edumyclient.base.component.GenericDialog
import com.baykal.edumyclient.base.component.LoadingIndicator
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.ui.MainState
import com.baykal.edumyclient.ui.menu.MenuItem
import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute
import com.baykal.edumyclient.ui.screen.account.register.RegisterRoute
import com.baykal.edumyclient.ui.screen.appUsage.AppUsageRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classroom.ClassroomRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import com.baykal.edumyclient.ui.screen.classroomSection.createClass.CreateClassRoute
import com.baykal.edumyclient.ui.screen.performanceSection.performances.PerformancesRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun EdumyComponent(state: MutableStateFlow<MainState>) {
    val navController = rememberNavController()
    val mainState by state.collectAsState()

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        EdumyClientTheme {
            Scaffold(
                topBar = {
                    AnimatedVisibility(
                        visible = mainState.topBarVisibility,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        EdumyToolbar(
                            navHostController = navController,
                            title = mainState.title,
                            login = mainState.loggedIn == true,
                            topLevelScreen = setOf(
                                ClassroomsRoute.route,
                                QuestionsRoute.route,
                                PerformancesRoute.route,
                                AppUsageRoute.route
                            )
                        )
                    }
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = mainState.bottomBarVisibility,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        EdumyBottomBar(
                            navHostController = navController,
                            items = MenuItem.bottomNavigationItems,
                        )
                    }
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = mainState.startRoute,
                    modifier = Modifier
                        .padding(it)
                ) {
                    LoginRoute.composable(this, navController, state)
                    RegisterRoute.composable(this, navController, state)
                    ProfileRoute.composable(this, navController, state)
                    ClassroomsRoute.composable(this, navController, state)
                    ClassroomRoute.composable(this, navController, state)
                    CreateClassRoute.composable(this, navController, state)
                    QuestionsRoute.composable(this, navController, state)
                    PerformancesRoute.composable(this, navController, state)
                    AppUsageRoute.composable(this, navController, state)
                }
                mainState.dialog?.let { dialog ->
                    GenericDialog(
                        title = dialog.title,
                        message = dialog.message,
                        onDismiss = dialog.onDismiss
                    )
                }
            }
            AnimatedVisibility(
                visible = mainState.loading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LoadingIndicator()
            }
        }
    }
}