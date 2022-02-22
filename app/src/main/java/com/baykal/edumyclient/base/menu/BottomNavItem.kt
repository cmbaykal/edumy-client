package com.baykal.edumyclient.base.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var route: String
) {
    object Classrooms : BottomNavItem("Classrooms", Icons.Filled.Home, "classrooms")
    object Questions : BottomNavItem("Questions", Icons.Filled.Home, "questions")
    object Performances : BottomNavItem("Performances", Icons.Filled.Home, "performances")
    object Usages : BottomNavItem("Usages", Icons.Filled.Home, "usages")
}