package com.baykal.edumyclient.ui.screen.appUsage

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object AppUsageRoute : NavRoute<AppUsageViewModel> {
    override val title = R.string.app_usages_screen
    override val route = "usages"
    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: AppUsageViewModel) {
        AppUsageScreen(viewModel)
    }

    @Composable
    override fun viewModel(): AppUsageViewModel = hiltViewModel()
}