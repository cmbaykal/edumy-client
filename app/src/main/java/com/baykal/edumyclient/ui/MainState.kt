package com.baykal.edumyclient.ui

import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute

data class MainState(
    val loggedIn: Boolean? = null,
    val title: String = "",
    val bottomBarVisibility: Boolean = false,
    val topBarVisibility: Boolean = false,
    val loading: Boolean = false,
    val dialog: DialogState? = null
) {
    val startRoute
        get() = if (loggedIn == true) {
            ClassroomsRoute.route
        } else {
            LoginRoute.route
        }
}

data class DialogState(
    val title: String,
    val message: String,
    val onDismiss: () -> Unit = {}
)