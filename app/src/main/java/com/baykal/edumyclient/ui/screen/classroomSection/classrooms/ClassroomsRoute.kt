package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object ClassroomsRoute : NavRoute<ClassroomsViewModel> {
    override val title = R.string.classrooms_screen
    override val route = "classrooms"
    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: ClassroomsViewModel) {
        ClassroomsScreen(viewModel)
    }

    @Composable
    override fun viewModel(): ClassroomsViewModel = hiltViewModel()
}