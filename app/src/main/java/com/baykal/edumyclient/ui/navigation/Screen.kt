package com.baykal.edumyclient.ui.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.baykal.edumyclient.R

sealed class Screen(
    val route: String,
    val title: Int,
    val icon: ImageVector = Icons.Filled.Home
) {
    object Login : Screen("login", R.string.login_screen)
    object Register : Screen("register", R.string.register_screen)
    object Home : Screen("home", R.string.home_screen)
    object Profile : Screen("profile", R.string.profile_screen)
    object Classrooms : Screen("classrooms", R.string.classrooms_screen, Icons.Filled.Home)
    object CreateClass : Screen("createClass", R.string.create_class_screen)
    object Performances : Screen("performances", R.string.performances_screen, Icons.Filled.Done)
    object AddPerformance : Screen("addPerformance", R.string.add_performance_screen)
    object AskQuestion : Screen("askQuestion", R.string.ask_question_screen, Icons.Filled.Send)
    object QuestionDetail : Screen("questionDetail", R.string.question_detail_screen)
    object Questions : Screen("questions", R.string.questions)
    object AppUsages : Screen("appUsages", R.string.app_usages_screen, Icons.Filled.DateRange)
    object Meetings : Screen("meetings", R.string.meetings_screen, Icons.Filled.PlayArrow)
    object ScheduleMeeting : Screen("scheduleMeeting", R.string.schedule_meeting_screen)
}