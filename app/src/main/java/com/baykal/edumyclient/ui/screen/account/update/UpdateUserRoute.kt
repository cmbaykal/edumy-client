package com.baykal.edumyclient.ui.screen.account.update

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object UpdateUserRoute : NavRoute<UpdateUserViewModel> {
    override val title = R.string.edit_profile_screen
    override val route = "editProfile"

    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: UpdateUserViewModel) {
        UpdateUserScreen(viewModel)
    }

    @Composable
    override fun viewModel(): UpdateUserViewModel = hiltViewModel()
}