package com.baykal.edumyclient.ui.screen.classroomSection.classroomDetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object ClassroomDetailRoute : NavRoute<ClassroomDetailViewModel> {
    const val CLASS_ID = "classId"

    override val title = R.string.classroom_detail_screen
    override val route = "classroom/{$CLASS_ID}"

    fun get(classId: String) = route.replace("{$CLASS_ID}", classId)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(CLASS_ID) {
            type = NavType.StringType
        }
    )

    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: ClassroomDetailViewModel) {
        ClassroomDetailScreen(viewModel)
    }

    @Composable
    override fun viewModel(): ClassroomDetailViewModel = hiltViewModel()
}