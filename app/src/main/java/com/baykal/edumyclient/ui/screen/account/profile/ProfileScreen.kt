package com.baykal.edumyclient.ui.screen.account.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.baykal.edumyclient.base.component.ScreenButton
import com.baykal.edumyclient.base.ui.theme.Orange
import com.baykal.edumyclient.ui.component.ProfileCard
import com.baykal.edumyclient.ui.screen.account.update.UpdateUserRoute
import com.baykal.edumyclient.ui.screen.questionSection.answers.AnswersRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    with(viewState) {
        LaunchedEffect(this.user) {
            if (user == null)
                viewModel.getUserInformation()
        }

        user?.let {
            ConstraintLayout(
                constraintSet = ScreenConstraints,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Orange)
                        .layoutId("topBox")
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .layoutId("bottomBox")
                )
                ProfileCard(
                    modifier = Modifier.layoutId("profileBox"),
                    user = it
                ) {
                    viewModel.navigate(UpdateUserRoute.route)
                }
                Column(
                    modifier = Modifier
                        .layoutId("screenButtons")
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 20.dp
                        )
                ) {
                    ScreenButton(
                        text = "Questions",
                        icon = Icons.Filled.QuestionAnswer
                    ) {
                        it.id?.let { userId ->
                            viewModel.navigate(QuestionsRoute.userQuestions(userId))
                        }
                    }
                    ScreenButton(
                        text = "Answers",
                        icon = Icons.Filled.RateReview
                    ) {
                        it.id?.let { userId ->
                            viewModel.navigate(AnswersRoute.userAnswers(userId))
                        }
                    }
                    ScreenButton(
                        text = "Performances",
                        icon = Icons.Filled.Leaderboard
                    ) {
                        // TODO : Navigate User Performances
                    }
                    ScreenButton(
                        text = "Usages",
                        icon = Icons.Filled.AccessTimeFilled
                    ) {
                        // TODO : Navigate User Usages
                    }
                    if (currentUser) {
                        ScreenButton(
                            text = "Logout",
                            icon = Icons.Filled.Logout
                        ) {
                            viewModel.logout()
                        }
                    }
                }
            }
        }
    }
}

val ScreenConstraints
    get() = ConstraintSet {
        val topBox = createRefFor("topBox")
        val bottomBox = createRefFor("bottomBox")
        val profileBox = createRefFor("profileBox")
        val screenButtons = createRefFor("screenButtons")

        constrain(topBox) {
            top.linkTo(parent.top)
            height = Dimension.percent(0.2f)
        }
        constrain(bottomBox) {
            top.linkTo(topBox.bottom)
            bottom.linkTo(parent.bottom)
            height = Dimension.percent(0.8f)
        }
        constrain(profileBox) {
            top.linkTo(topBox.bottom)
            bottom.linkTo(topBox.bottom)
        }
        constrain(screenButtons) {
            top.linkTo(profileBox.bottom)
        }
    }