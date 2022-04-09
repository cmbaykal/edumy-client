package com.baykal.edumyclient.ui.screen.meetingSection.meetings

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.base.nav.NavRoute

object MeetingsRoute : NavRoute<MeetingsViewModel> {
    const val USER_ID = "userId"

    override val title = "Meetings"
    override val route = "meetings?userId={${USER_ID}}"

    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    fun get(userId: String) = route.replace("{${USER_ID}}", userId)

    @Composable
    override fun Content(viewModel: MeetingsViewModel) {
        MeetingsScreen(viewModel)
    }

    @Composable
    override fun viewModel(): MeetingsViewModel = hiltViewModel()

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(USER_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        }
    )
}