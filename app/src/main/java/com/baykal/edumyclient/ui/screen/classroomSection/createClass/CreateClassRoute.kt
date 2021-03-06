package com.baykal.edumyclient.ui.screen.classroomSection.createClass

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object CreateClassRoute : NavRoute<CreateClassViewModel> {
    override val title = R.string.create_classroom_screen
    override val route = "createClass"

    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: CreateClassViewModel) {
        CreateClassScreen(viewModel)
    }

    @Composable
    override fun viewModel(): CreateClassViewModel = hiltViewModel()

}