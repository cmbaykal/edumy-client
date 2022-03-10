package com.baykal.edumyclient.ui.screen.account.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.base.nav.NavRoute

object ProfileRoute : NavRoute<ProfileViewModel> {
    override val title = "Profile"
    override val route = "profile"
    override fun topBarVisibility() = true
    override fun bottomBarVisibility() = false

    @Composable
    override fun Content(viewModel: ProfileViewModel) {
        ProfileScreen(viewModel)
    }

    @Composable
    override fun viewModel(): ProfileViewModel = hiltViewModel()
}