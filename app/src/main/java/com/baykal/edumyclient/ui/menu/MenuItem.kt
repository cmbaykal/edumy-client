package com.baykal.edumyclient.ui.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MenuItem(
    var title: String,
    var icon: ImageVector,
    var route: String
) {
    // Bottom Navigation
    object Classrooms : MenuItem("Classrooms", Icons.Filled.Book, "classrooms")
    object Questions : MenuItem("Questions", Icons.Filled.QuestionAnswer, "questions")
    object Performances : MenuItem("Performances", Icons.Filled.Leaderboard, "performances")
    object Usages : MenuItem("Usages", Icons.Filled.AccessTimeFilled, "usages")
}