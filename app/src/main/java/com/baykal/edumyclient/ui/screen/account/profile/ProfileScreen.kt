package com.baykal.edumyclient.ui.screen.account.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.baykal.edumyclient.base.component.EIconButton
import com.baykal.edumyclient.base.component.ScreenButton
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange
import com.baykal.edumyclient.data.model.user.response.User
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    with(viewState) {
        user?.also {
            ConstraintLayout(
                constraintSet = ScreenConstraints,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-10).dp)
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
                    // TODO: Navigate to Edit Profile
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
                        text = "Classrooms",
                        icon = Icons.Filled.Class
                    ) {
                        viewModel.navigate(ClassroomsRoute.route)
                    }
                    ScreenButton(
                        text = "Questions",
                        icon = Icons.Filled.QuestionAnswer
                    ) {
                        // TODO : Navigate User Questions
                    }
                    ScreenButton(
                        text = "Answers",
                        icon = Icons.Filled.RateReview
                    ) {
                        // TODO : Navigate User Answers
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
    } ?: run {
        viewModel.getUserInformation()
    }
}

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    user: User,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                start = 40.dp,
                end = 40.dp
            )
            .then(
                modifier
            ),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp
    ) {
        Box {
            EIconButton(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.TopEnd),
                icon = Icons.Filled.Edit,
                iconSize = 20.dp,
                onClick = onEditClick
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(80.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    tint = Gray,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = user.name.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = user.role.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
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