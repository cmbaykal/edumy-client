package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.base.nav.NavRoute

object ClassroomRoute : NavRoute<ClassroomViewModel> {
    override val title = "Classroom"
    override val route = "class"

    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: ClassroomViewModel) {
        ClassroomScreen(viewModel)
    }

    @Composable
    override fun viewModel(): ClassroomViewModel = hiltViewModel()

}