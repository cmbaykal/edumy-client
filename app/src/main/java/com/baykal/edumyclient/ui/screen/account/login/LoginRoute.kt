package com.baykal.edumyclient.ui.screen.account.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.base.nav.NavRoute

object LoginRoute : NavRoute<LoginViewModel> {
    override val title = "Login"
    override val route = "login"

    @Composable
    override fun Content(viewModel: LoginViewModel) {
        LoginScreen(viewModel)
    }

    @Composable
    override fun viewModel(): LoginViewModel = hiltViewModel()
}