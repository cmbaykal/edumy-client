package com.baykal.edumyclient.ui.screen.questionSection.answers

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object AnswersRoute : NavRoute<AnswersViewModel> {
    const val USER_ID = "userId"
    const val CLASS_ID = "classId"
    const val BASE_TITLE = R.string.answers_screen

    override var title = BASE_TITLE
    override var route = "answers?userId={${USER_ID}}&classId={$CLASS_ID}"

    fun userAnswers(userId: String): String {
        title = R.string.user_answers_screen
        return route.replace("{${USER_ID}}", userId)
    }

    fun classAnswers(classId: String): String {
        title = R.string.classroom_answers_screen
        return route.replace("{${CLASS_ID}}", classId)
    }

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(USER_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        },
        navArgument(CLASS_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        }
    )

    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: AnswersViewModel) {
        AnswersScreen(viewModel)
    }

    @Composable
    override fun viewModel(): AnswersViewModel = hiltViewModel()
}