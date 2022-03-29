package com.baykal.edumyclient.ui.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.ui.graphics.vector.ImageVector
import com.baykal.edumyclient.ui.screen.appUsage.AppUsageRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import com.baykal.edumyclient.ui.screen.performanceSection.performances.PerformancesRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute

sealed class MenuItem(
    var title: String,
    var icon: ImageVector,
    var route: String
) {
    // Bottom Navigation
    object Classrooms : MenuItem(ClassroomsRoute.title, Icons.Filled.Book, ClassroomsRoute.route)
    object Questions : MenuItem(QuestionsRoute.BASE_TITLE, Icons.Filled.QuestionAnswer, QuestionsRoute.route)
    object Performances : MenuItem(PerformancesRoute.title, Icons.Filled.Leaderboard, PerformancesRoute.route)
    object Usages : MenuItem(AppUsageRoute.title, Icons.Filled.AccessTimeFilled, AppUsageRoute.route)

    companion object {
        val bottomNavigationItems
            get() = listOf(
                Classrooms,
                Questions,
                Performances,
                Usages
            )
    }

}