package com.baykal.edumyclient.ui.screen.classroomSection.classroomDetail

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.GrayLight
import com.baykal.edumyclient.base.ui.theme.Orange
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute
import com.baykal.edumyclient.ui.screen.questionSection.answers.AnswersRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute

@Composable
fun ClassroomDetailScreen(
    viewModel: ClassroomDetailViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    var assignDialog by remember { mutableStateOf(false) }

    with(viewState) {
        LaunchedEffect(this.classroom) {
            if (classroom == null)
                viewModel.getClassroomInformation()
        }

        classroom?.let {
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
                ClassroomCard(
                    modifier = Modifier.layoutId("classroomBox"),
                    classroom = it
                )
                Row(
                    modifier = Modifier
                        .layoutId("studentsBox")
                        .padding(
                            start = 40.dp,
                            end = 40.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StudentsComponent(
                        modifier = Modifier.weight(8f),
                        classroom = it
                    ) { userId ->
                        if (userId != null) {
                            viewModel.navigate(ProfileRoute.get(userId))
                        }
                    }
                    if (viewState.user?.role == UserRole.Teacher) {
                        EIconButton(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .weight(2f),
                            icon = Icons.Filled.Add
                        ) {
                            assignDialog = true
                        }
                    }
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
                        it.id?.let { classId ->
                            viewModel.navigate(QuestionsRoute.classQuestions(classId))
                        }
                    }
                    ScreenButton(
                        text = "Answers",
                        icon = Icons.Filled.RateReview
                    ) {
                        it.id?.let { classId ->
                            viewModel.navigate(AnswersRoute.classAnswers(classId))
                        }
                    }
                    ScreenButton(
                        text = "Performances",
                        icon = Icons.Filled.Leaderboard
                    ) {
                        TODO("Classroom Performances Navigation")
                    }
                    it.id?.let { classId ->
                        if (!owner) {
                            ScreenButton(
                                text = "Leave Classroom",
                                icon = Icons.Filled.ExitToApp
                            ) {
                                viewModel.leaveClassroom(classId)
                            }
                        } else if (classroom.users?.size == 1) {
                            ScreenButton(
                                text = "Delete Classroom",
                                icon = Icons.Filled.RemoveCircle
                            ) {
                                viewModel.deleteClassroom(classId)
                            }
                        }
                    }
                }
            }
            if (assignDialog) {
                AssignDialog(onDismiss = { assignDialog = false }) { state ->
                    if (state.isSuccess) {
                        assignDialog = false
                        viewModel.assignUser(it.id, state.text)
                    }
                }
            }
        }
    }
}

@Composable
fun ClassroomCard(
    modifier: Modifier = Modifier,
    classroom: Classroom
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(60.dp),
                    imageVector = Icons.Filled.Class,
                    tint = Gray,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = classroom.name.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Lesson - ${classroom.lesson.toString()}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = classroom.classSize,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
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
            .then(modifier)
            .padding(top = 10.dp),
        elevation = 4.dp,
    ) {
        ECollapsableLayout(
            collapsedContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(20.dp),
                        imageVector = Icons.Filled.Group,
                        tint = Gray,
                        contentDescription = ""
                    )
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
                }
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

val ScreenConstraints
    get() = ConstraintSet {
        val topBox = createRefFor("topBox")
        val bottomBox = createRefFor("bottomBox")
        val classroomBox = createRefFor("classroomBox")
        val studentsBox = createRefFor("studentsBox")
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
        constrain(classroomBox) {
            top.linkTo(topBox.bottom)
            bottom.linkTo(topBox.bottom)
        }
        constrain(studentsBox) {
            top.linkTo(classroomBox.bottom)
        }
        constrain(screenButtons) {
            top.linkTo(studentsBox.bottom)
        }
    }