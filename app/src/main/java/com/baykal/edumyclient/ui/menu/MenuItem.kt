package com.baykal.edumyclient.ui.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.baykal.edumyclient.ui.screen.appUsage.AppUsageRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import com.baykal.edumyclient.ui.screen.meetingSection.meetings.MeetingsRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute
import com.baykal.edumyclient.ui.screen.studiesSection.studies.StudiesRoute

sealed class MenuItem(
    var title: String,
    var icon: ImageVector,
    var route: String
) {
    // Bottom Navigation
    object Classrooms : MenuItem(ClassroomsRoute.title, Icons.Filled.Book, ClassroomsRoute.route)
    object Questions : MenuItem(QuestionsRoute.BASE_TITLE, Icons.Filled.QuestionAnswer, QuestionsRoute.route)
    object Studies : MenuItem(StudiesRoute.title, Icons.Filled.Leaderboard, StudiesRoute.route)
    object Usages : MenuItem(AppUsageRoute.title, Icons.Filled.AccessTimeFilled, AppUsageRoute.route)
    object Meetings : MenuItem(MeetingsRoute.title, Icons.Filled.People, MeetingsRoute.route)

    companion object {
        val bottomNavigationItems
            get() = listOf(
                Classrooms,
                Questions,
                Studies,
                Meetings
            )
    }
}