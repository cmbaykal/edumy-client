package com.baykal.edumyclient.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.baykal.edumyclient.ui.MainState
import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import com.baykal.edumyclient.ui.screen.account.register.RegisterRoute
import com.baykal.edumyclient.ui.screen.appUsage.AppUsageRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import com.baykal.edumyclient.ui.screen.performanceSection.performances.PerformancesRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute

@Composable
fun NavigationComponent(navHostController: NavHostController, mainState: MutableState<MainState>, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = LoginRoute.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        LoginRoute.composable(this, navHostController, mainState)
        RegisterRoute.composable(this, navHostController, mainState)
        ClassroomsRoute.composable(this, navHostController, mainState)
        QuestionsRoute.composable(this, navHostController, mainState)
        PerformancesRoute.composable(this, navHostController, mainState)
        AppUsageRoute.composable(this, navHostController, mainState)
    }
}