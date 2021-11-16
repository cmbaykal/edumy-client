package com.baykal.edumyclient.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baykal.edumyclient.ui.navigation.Screen
import com.baykal.edumyclient.ui.screen.account.profile.ProfileScreen
import com.baykal.edumyclient.ui.screen.appUsage.AppUsageScreen
import com.baykal.edumyclient.ui.screen.askQuestion.askquestion.AskQuestionScreen
import com.baykal.edumyclient.ui.screen.askQuestion.questionDetail.QuestionDetailScreen
import com.baykal.edumyclient.ui.screen.askQuestion.questions.QuestionsScreen
import com.baykal.edumyclient.ui.screen.classroom.classrooms.ClassRoomsScreen
import com.baykal.edumyclient.ui.screen.classroom.createClass.CreateClassScreen
import com.baykal.edumyclient.ui.screen.meeting.MeetingsScreen
import com.baykal.edumyclient.ui.screen.meeting.scheduleMeeting.ScheduleMeetingScreen
import com.baykal.edumyclient.ui.screen.performance.addPerformance.AddPerformanceScreen
import com.baykal.edumyclient.ui.screen.performance.performances.PerformancesScreen

@Composable
fun HomeScreen() {

    val navController = rememberNavController()

    val items = listOf(
        Screen.Classrooms,
        Screen.Performances,
        Screen.Questions,
        Screen.Meetings,
        Screen.AppUsages,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Class Rooms") },
                backgroundColor = Color.Black,
                contentColor = Color.White,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Profile.route)
                        }
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Classrooms.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Classrooms.route) { ClassRoomsScreen(navController) }
            composable(Screen.Performances.route) { PerformancesScreen(navController) }
            composable(Screen.Questions.route) { QuestionsScreen(navController) }
            composable(Screen.Meetings.route) { MeetingsScreen(navController) }
            composable(Screen.AppUsages.route) { AppUsageScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.CreateClass.route) { CreateClassScreen() }
            composable(Screen.AddPerformance.route){ AddPerformanceScreen()}
            composable(Screen.AskQuestion.route){ AskQuestionScreen() }
            composable(Screen.QuestionDetail.route){ QuestionDetailScreen() }
            composable(Screen.ScheduleMeeting.route){ ScheduleMeetingScreen()}
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}