package com.baykal.edumyclient.ui.screen.account.register

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object RegisterRoute : NavRoute<RegisterViewModel> {
    override val title = R.string.register_screen
    override val route = "register"

    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: RegisterViewModel) {
        RegisterScreen(viewModel)
    }

    @Composable
    override fun viewModel(): RegisterViewModel = hiltViewModel()
}