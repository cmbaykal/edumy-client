package com.baykal.edumyclient.ui.screen.questionSection.answers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EList
import com.baykal.edumyclient.base.component.ImageDialog
import com.baykal.edumyclient.ui.component.AnswerCard
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute
import com.baykal.edumyclient.ui.screen.questionSection.questionDetail.QuestionDetailRoute

@Composable
fun AnswersScreen(
    viewModel: AnswersViewModel
) {
    val viewState by viewModel.uiState.collectAsState()

    with(viewState) {
        LaunchedEffect(answers) {
            if (answers == null) {
                viewModel.fetchData()
            }
        }

        answers?.let { list ->
            EList(
                modifier = Modifier.fillMaxSize(),
                swipeRefresh = true,
                onRefresh = { viewModel.getAnswers(true) },
                items = list
            ) {
                AnswerCard(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 6.dp, top = 6.dp),
                    userId = userId.toString(),
                    answer = it,
                    onUpVote = {
                        viewModel.upVoteAnswer(it)
                    },
                    onDownVote = {
                        viewModel.downVoteAnswer(it)
                    },
                    onProfileClick = {
                        viewModel.navigate(ProfileRoute.get(userId.toString()))
                    },
                    onImageClick = { uri ->
                        viewModel.setImageUri(uri)
                    }
                ) { questionId ->
                    viewModel.navigate(QuestionDetailRoute.get(questionId))
                }
            }
        }

        if (imageDialogState) {
            ImageDialog(file = imageUri.toString(), description = "Answer Image") {
                viewModel.setImageDialog(false)
            }
        }
    }
}