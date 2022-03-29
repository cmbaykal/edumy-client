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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.ImageDialog
import com.baykal.edumyclient.ui.component.AnswerCard
import com.baykal.edumyclient.ui.component.ProfileCardCompact
import com.baykal.edumyclient.ui.component.QuestionCard
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute
import com.baykal.edumyclient.ui.screen.questionSection.sendAnswer.SendAnswerRoute

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
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                question.user?.let { user ->
                    ProfileCardCompact(
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp),
                        user = user
                    ) {
                        viewModel.navigate(ProfileRoute.get(it))
                    }
                }
                QuestionCard(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 12.dp),
                    question = question
                )
                Row(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 12.dp, bottom = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    EButton(
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .fillMaxWidth()
                            .weight(0.4f),
                        shape = RoundedCornerShape(50.dp),
                        text = "Answers",
                        textSize = 12.sp
                    ) {
                        question.id?.let {
                            viewModel.fetchAnswers(it)
                        }
                    }
                    EButton(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .fillMaxWidth()
                            .weight(0.4f),
                        shape = RoundedCornerShape(50.dp),
                        text = "Write Answer",
                        textSize = 12.sp
                    ) {
                        question.id?.let {
                            viewModel.navigate(SendAnswerRoute.get(it))
                        }
                    }
                }
                answers.forEach {
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