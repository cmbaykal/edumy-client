package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.base.nav.NavRoute


object ClassroomRoute : NavRoute<ClassroomViewModel> {
    const val CLASS_ID = "classId"

    override val title = "Classroom"
    override val route = "classroom/{$CLASS_ID}"

    fun get(classId: String) = route.replace("{$CLASS_ID}", classId)

    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: ClassroomViewModel) {
        ClassroomScreen(viewModel)
    }

    @Composable
    override fun viewModel(): ClassroomViewModel = hiltViewModel()

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(CLASS_ID) { type = NavType.StringType }
    )
}