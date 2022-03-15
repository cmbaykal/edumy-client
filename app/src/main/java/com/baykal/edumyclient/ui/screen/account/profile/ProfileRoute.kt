package com.baykal.edumyclient.ui.screen.account.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.base.nav.NavRoute

object ProfileRoute : NavRoute<ProfileViewModel> {
    const val USER_ID = "userId"
    const val DEFAULT = "me"

    override val title = "Profile"
    override val route = "profile?userId={$USER_ID}"

    fun get(userId: String) = route.replace("{${USER_ID}}", userId)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(USER_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        }
    )

    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: ProfileViewModel) {
        ProfileScreen(viewModel)
    }

    @Composable
    override fun viewModel(): ProfileViewModel = hiltViewModel()
}