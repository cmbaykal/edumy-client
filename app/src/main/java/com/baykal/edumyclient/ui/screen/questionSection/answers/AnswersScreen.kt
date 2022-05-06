package com.baykal.edumyclient.ui.screen.questionSection.answers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.EList
import com.baykal.edumyclient.base.component.ImageDialog
import com.baykal.edumyclient.base.component.ListSwipeRefreshSettings
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

        EList(
            modifier = Modifier.fillMaxSize(),
            swipeRefreshSettings = ListSwipeRefreshSettings(
                enabled = true,
                onRefresh = { viewModel.getAnswers(true) }
            ),
            listItems = answers
        ) {
            AnswerCard(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_standard),
                    end = dimensionResource(id = R.dimen.padding_standard),
                    bottom = dimensionResource(id = R.dimen.padding_medium),
                    top = dimensionResource(id = R.dimen.padding_medium)
                ),
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

        if (imageDialogState) {
            ImageDialog(file = imageUri.toString(), description = "Answer Image") {
                viewModel.setImageDialog(false)
            }
        }
    }
}