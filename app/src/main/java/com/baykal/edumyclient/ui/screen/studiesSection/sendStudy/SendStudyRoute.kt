package com.baykal.edumyclient.ui.screen.studiesSection.sendStudy

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.base.nav.NavRoute

object SendStudyRoute : NavRoute<SendStudyViewModel> {
    override val title = "Add Study"
    override val route = "addStudy"
    override fun bottomBarVisibility() = false
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: SendStudyViewModel) {
        SendStudyScreen(viewModel)
    }

    @Composable
    override fun viewModel(): SendStudyViewModel = hiltViewModel()
}