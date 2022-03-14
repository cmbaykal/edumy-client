package com.baykal.edumyclient.ui.screen.questionSection.askquestion

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.base.nav.NavRoute

object AskQuestionRoute : NavRoute<AskQuestionViewModel> {

    override val title = "Ask Question"
    override val route = "askQuestion"

    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: AskQuestionViewModel) {
        AskQuestionScreen(viewModel)
    }

    @Composable
    override fun viewModel(): AskQuestionViewModel = hiltViewModel()
}