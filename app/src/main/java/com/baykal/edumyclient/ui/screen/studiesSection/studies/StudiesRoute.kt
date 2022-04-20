package com.baykal.edumyclient.ui.screen.studiesSection.studies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object StudiesRoute : NavRoute<StudiesViewModel> {
    const val USER_ID = "userId"

    override val title = R.string.studies_screen
    override val route = "studies?userId={${USER_ID}}"

    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    fun get(userId: String) = route.replace("{${USER_ID}}", userId)

    @Composable
    override fun Content(viewModel: StudiesViewModel) {
        StudiesScreen(viewModel)
    }

    @Composable
    override fun viewModel(): StudiesViewModel = hiltViewModel()

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(USER_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        }
    )
}