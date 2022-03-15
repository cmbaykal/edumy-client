package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.base.nav.NavRoute

object QuestionsRoute : NavRoute<QuestionsViewModel> {
    const val USER_ID = "userId"
    const val CLASS_ID = "classId"

    override val title = "Questions"
    override val route = "questions?userId={${USER_ID}}&classId={$CLASS_ID}"

    fun userQuestions(userId: String) = route.replace("{${USER_ID}}", userId)
    fun classQuestions(classId: String) = route.replace("{${CLASS_ID}}", classId)

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
    override fun Content(viewModel: QuestionsViewModel) {
        QuestionsScreen(viewModel)
    }

    @Composable
    override fun viewModel(): QuestionsViewModel = hiltViewModel()
}