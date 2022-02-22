package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.base.nav.NavRoute

object QuestionsRoute : NavRoute<QuestionsViewModel> {
    override val title = "Questions"
    override val route = "questions"
    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: QuestionsViewModel) {
        QuestionsScreen(viewModel)
    }

    @Composable
    override fun viewModel(): QuestionsViewModel = hiltViewModel()
}