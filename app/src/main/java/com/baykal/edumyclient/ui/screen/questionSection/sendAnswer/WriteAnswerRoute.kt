package com.baykal.edumyclient.ui.screen.questionSection.sendAnswer

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object WriteAnswerRoute : NavRoute<WriteAnswerViewModel> {
    const val QUESTION_ID = "questionId"

    override val title = R.string.answer_question_screen
    override val route = "question/answer/{$QUESTION_ID}"

    fun get(questionId: String) = route.replace("{${QUESTION_ID}}", questionId)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(QUESTION_ID) {
            type = NavType.StringType
        }
    )

    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: WriteAnswerViewModel) {
        WriteAnswerScreen(viewModel)
    }

    @Composable
    override fun viewModel(): WriteAnswerViewModel = hiltViewModel()
}