package com.baykal.edumyclient.ui.screen.studiesSection.studies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute

object StudiesRoute : NavRoute<StudiesViewModel> {
    const val USER_ID = "userId"
    const val CLASS_ID = "classId"
    const val BASE_TITLE = R.string.studies_screen

    override var title = BASE_TITLE
    override val route = "studies?userId={${USER_ID}}&classId={${CLASS_ID}}"

    override fun bottomBarVisibility() = title == BASE_TITLE
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: StudiesViewModel) {
        StudiesScreen(viewModel)
    }

    @Composable
    override fun viewModel(): StudiesViewModel = hiltViewModel()

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(USER_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        },
        navArgument(QuestionsRoute.CLASS_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        }
    )

    override fun feed(): String {
        title = BASE_TITLE
        return route
    }

    fun userStudies(userId: String): String {
        title = R.string.user_studies_screen
        return route.replace("{${USER_ID}}", userId)
    }

    fun classroomStudies(classId: String): String {
        title = R.string.classroom_studies_screen
        return route.replace("{${CLASS_ID}}", classId)
    }
}