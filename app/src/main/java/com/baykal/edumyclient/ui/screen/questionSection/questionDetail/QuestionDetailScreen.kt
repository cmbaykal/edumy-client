package com.baykal.edumyclient.ui.screen.questionSection.questionDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.ImageDialog
import com.baykal.edumyclient.base.extension.fontDimensionResource
import com.baykal.edumyclient.ui.component.AnswerCard
import com.baykal.edumyclient.ui.component.ProfileCardCompact
import com.baykal.edumyclient.ui.component.QuestionCard
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute
import com.baykal.edumyclient.ui.screen.questionSection.sendAnswer.WriteAnswerRoute

@Composable
fun QuestionDetailScreen(
    viewModel: QuestionDetailViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    with(viewState) {
        LaunchedEffect(question) {
            if (question == null)
                viewModel.fetchData()
        }

        if (question != null) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(dimensionResource(id = R.dimen.padding_huge)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                question.user?.let { user ->
                    ProfileCardCompact(
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_standard),
                            end = dimensionResource(id = R.dimen.padding_standard),
                            top = dimensionResource(id = R.dimen.padding_standard)
                        ),
                        user = user
                    ) {
                        viewModel.navigate(ProfileRoute.get(it))
                    }
                }
                QuestionCard(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_standard),
                        end = dimensionResource(id = R.dimen.padding_standard),
                        top = dimensionResource(id = R.dimen.padding_standard)
                    ),
                    question = question
                )
                Row(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_standard),
                            end = dimensionResource(id = R.dimen.padding_standard),
                            top = dimensionResource(id = R.dimen.padding_standard),
                            bottom = dimensionResource(id = R.dimen.padding_medium)
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    EButton(
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.padding_medium))
                            .fillMaxWidth()
                            .weight(0.4f),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_massive)),
                        text = stringResource(id = R.string.answers_screen),
                        textSize = fontDimensionResource(id = R.dimen.font_size_small)
                    ) {
                        question.id?.let {
                            viewModel.fetchAnswers(it)
                        }
                    }
                    EButton(
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_medium))
                            .fillMaxWidth()
                            .weight(0.4f),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_massive)),
                        text = stringResource(id = R.string.write_answer_button),
                        textSize = fontDimensionResource(id = R.dimen.font_size_small)
                    ) {
                        question.id?.let {
                            viewModel.navigate(WriteAnswerRoute.get(it))
                        }
                    }
                }
                answers.forEach {
                    AnswerCard(
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_standard),
                            end = dimensionResource(id = R.dimen.padding_standard),
                            top = dimensionResource(id = R.dimen.padding_medium),
                            bottom = dimensionResource(id = R.dimen.padding_medium)
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
                    )
                }
            }
            if (imageDialogState) {
                ImageDialog(file = imageUri.toString(), description = "Answer Image") {
                    viewModel.setImageDialog(false)
                }
            }
        }
    }
}