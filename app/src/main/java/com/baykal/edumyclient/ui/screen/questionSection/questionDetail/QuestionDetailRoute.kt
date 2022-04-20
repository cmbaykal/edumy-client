package com.baykal.edumyclient.ui.screen.questionSection.questionDetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object QuestionDetailRoute : NavRoute<QuestionDetailViewModel> {
    const val QUESTION_ID = "questionId"

    override val title = R.string.question_detail_screen
    override val route = "question/{$QUESTION_ID}"

    fun get(questionId: String) = route.replace("{${QUESTION_ID}}", questionId)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(QUESTION_ID) {
            type = NavType.StringType
        }
    )

    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: QuestionDetailViewModel) {
        QuestionDetailScreen(viewModel)
    }

    @Composable
    override fun viewModel(): QuestionDetailViewModel = hiltViewModel()
}