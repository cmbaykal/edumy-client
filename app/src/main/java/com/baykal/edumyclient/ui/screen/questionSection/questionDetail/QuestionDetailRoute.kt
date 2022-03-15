package com.baykal.edumyclient.ui.screen.questionSection.questionDetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.base.nav.NavRoute

object QuestionDetailRoute : NavRoute<QuestionDetailViewModel> {
    const val QUESTIONID = "questionId"

    override val title = "Question Detail"
    override val route = "questions/{${QUESTIONID}"

    fun get(questionId: String) = route.replace("{${QUESTIONID}}", questionId)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(QUESTIONID) {
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