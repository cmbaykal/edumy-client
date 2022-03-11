package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.GrayLight
import com.baykal.edumyclient.base.ui.theme.Orange
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute

@Composable
fun ClassroomScreen(
    viewModel: ClassroomViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    var assignDialog by remember { mutableStateOf(false) }

    with(viewState) {
        classroom?.also {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(top = 40.dp),
                    imageVector = Icons.Filled.Class,
                    tint = Orange,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = it.name.toString(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp,
                            start = 20.dp
                        ),
                    text = "Lesson - ${it.lesson}",
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 10.dp,
                            start = 20.dp
                        ),
                    text = it.classSize,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    StudentsComponent(
                        modifier = Modifier.weight(8f),
                        classroom = it
                    ) { userId ->
                        if (userId != null) {
                            viewModel.navigate(ProfileRoute.get(userId))
                        }
                    }
                    if (viewState.user?.role == UserRole.Teacher) {
                        EButton(
                            modifier = Modifier
                                .padding(top = 2.dp, end = 12.dp)
                                .weight(2f),
                            text = "+",
                            textSize = 25.sp,
                            onClick = {
                                assignDialog = true
                            }
                        )
                    }
                }
                ScreenButton(
                    text = "Questions",
                    icon = Icons.Filled.QuestionAnswer
                ) {
                    TODO("Classroom Questions Navigation")
                }
                ScreenButton(
                    text = "Answers",
                    icon = Icons.Filled.RateReview
                ) {
                    TODO("Classroom Questions Navigation")
                }
                ScreenButton(
                    text = "Performances",
                    icon = Icons.Filled.Leaderboard
                ) {
                    TODO("Classroom Performances Navigation")
                }
                viewState.owner.also { owner ->
                    if (!owner) {
                        ScreenButton(text = "Leave Classroom") {
                            TODO("Leave Classroom Request")
                        }
                    } else if (it.users?.size == 1) {
                        ScreenButton(text = "Delete Classroom") {
                            TODO("Delete Classroom Request")
                        }
                    }
                }
            }
            if (assignDialog) {
                AssignDialog(onDismiss = { assignDialog = false }) { state ->
                    it.id?.let { classId ->
                        if (state.isSuccess) {
                            assignDialog = false
                            viewModel.assignUser(classId, state.text)
                        }
                    }
                }
            }
        } ?: run {
            viewModel.getClassroomInformation()
        }
    }
}

@Composable
fun StudentsComponent(
    modifier: Modifier = Modifier,
    classroom: Classroom,
    onClick: (String?) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .then(modifier),
        elevation = 4.dp,
    ) {
        ECollapsableLayout(
            collapsedContent = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 20.dp,
                            top = 15.dp,
                            bottom = 15.dp,
                        ),
                    text = "Students",
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            expandedContent = {
                Column {
                    classroom.users?.let { users ->
                        users.filter { it.role == UserRole.Student }.forEach { user ->
                            Box(
                                modifier = Modifier.clickable {
                                    onClick.invoke(user.id)
                                }
                            ) {
                                Divider(
                                    color = GrayLight,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .padding(start = 10.dp, end = 10.dp)
                                        .align(Alignment.TopCenter)
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .align(Alignment.CenterStart),
                                    text = user.name.toString(),
                                    textAlign = TextAlign.Start,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.CenterEnd),
                                    imageVector = Icons.Filled.KeyboardArrowRight,
                                    tint = Gray,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            },
            rotatingContent = {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    tint = Gray,
                    contentDescription = ""
                )
            }
        )
    }
}

@Composable
fun AssignDialog(
    onDismiss: () -> Unit,
    onClick: (InputState) -> Unit
) {
    var inputState by remember { mutableStateOf(InputState()) }
    val positiveButton = DialogButton("Assign") {
        onClick.invoke(inputState)
    }
    val negativeButton = DialogButton("Cancel", onClick = onDismiss)

    EDialog(
        title = "Assign User",
        onDismiss = onDismiss,
        positiveButton = positiveButton,
        negativeButton = negativeButton
    ) {
        ETextField(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            label = "E-mail",
            onChange = { inputState = it },
            success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
            imeAction = ImeAction.Done,
            onAction = {
                onClick.invoke(inputState)
            }
        )
    }
}