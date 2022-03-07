package com.baykal.edumyclient.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.baykal.edumyclient.ui.screen.account.register.RegisterRoute
import com.baykal.edumyclient.ui.screen.appUsage.AppUsageRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classroom.ClassroomRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import com.baykal.edumyclient.ui.screen.classroomSection.createClass.CreateClassRoute
import com.baykal.edumyclient.ui.screen.performanceSection.performances.PerformancesRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun EdumyComponent(startRoute: String) {
    val navController = rememberNavController()
    val mainState = remember { mutableStateOf(MainState()) }

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        EdumyClientTheme {
            Scaffold(
                topBar = {
                    EdumyToolbar(
                        navHostController = navController,
                        title = mainState.value.title,
                        visibility = mainState.value.topBarVisibility,
                        topLevelScreen = setOf(
                            ClassroomsRoute.route,
                            QuestionsRoute.route,
                            PerformancesRoute.route,
                            AppUsageRoute.route
                        )
                    )
                },
                bottomBar = {
                    EdumyBottomBar(
                        navHostController = navController,
                        items = MenuItem.bottomNavigationItems,
                        visibility = mainState.value.bottomBarVisibility
                    )
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = startRoute,
                    modifier = Modifier.padding(it)
                ) {
                    LoginRoute.composable(this, navController, mainState)
                    RegisterRoute.composable(this, navController, mainState)
                    ClassroomsRoute.composable(this, navController, mainState)
                    ClassroomRoute.composable(this, navController, mainState)
                    CreateClassRoute.composable(this, navController, mainState)
                    QuestionsRoute.composable(this, navController, mainState)
                    PerformancesRoute.composable(this, navController, mainState)
                    AppUsageRoute.composable(this, navController, mainState)
                }
                with(mainState.value) {
                    dialog?.let { dialog ->
                        GenericDialog(
                            title = dialog.title,
                            message = dialog.message,
                            onDismiss = dialog.onDismiss
                        )
                    }
                    if (loading) {
                        LoadingIndicator()
                    }
                }
            }
        }
    }
}