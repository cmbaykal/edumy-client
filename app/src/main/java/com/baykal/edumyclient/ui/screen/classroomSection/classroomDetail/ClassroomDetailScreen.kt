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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.extension.fontDimensionResource
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
                            start = dimensionResource(id = R.dimen.padding_huge),
                            end = dimensionResource(id = R.dimen.padding_huge)
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
                                .padding(top = dimensionResource(id = R.dimen.padding_standard))
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
                            start = dimensionResource(id = R.dimen.padding_big),
                            end = dimensionResource(id = R.dimen.padding_big),
                            bottom = dimensionResource(id = R.dimen.padding_big)
                        )
                ) {
                    ScreenButton(
                        text = stringResource(id = R.string.questions_screen),
                        icon = Icons.Filled.QuestionAnswer
                    ) {
                        it.id?.let { classId ->
                            viewModel.navigate(QuestionsRoute.classQuestions(classId))
                        }
                    }
                    ScreenButton(
                        text = stringResource(id = R.string.answers_screen),
                        icon = Icons.Filled.RateReview
                    ) {
                        it.id?.let { classId ->
                            viewModel.navigate(AnswersRoute.classAnswers(classId))
                        }
                    }
                    it.id?.let { classId ->
                        if (!owner) {
                            ScreenButton(
                                text = stringResource(id = R.string.leave_classroom_button),
                                icon = Icons.Filled.ExitToApp
                            ) {
                                viewModel.leaveClassroom(classId)
                            }
                        } else if (classroom.users?.size == 1) {
                            ScreenButton(
                                text = stringResource(id = R.string.delete_classroom_button),
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
    val resources = LocalContext.current.resources

    with(classroom){
        Card(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_huge),
                    end = dimensionResource(id = R.dimen.padding_huge)
                )
                .then(
                    modifier
                ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard)),
            elevation = dimensionResource(id = R.dimen.elevation_big)
        ) {
            Box {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.padding_big))
                            .size(dimensionResource(id = R.dimen.classroom_card_icon_size)),
                        imageVector = Icons.Filled.Class,
                        tint = Gray,
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_standard)),
                        text = name.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_big),
                        fontWeight = FontWeight.Bold
                    )
                    val teacherName = users?.first()?.name.toString()
                    Text(
                        text = stringResource(id = R.string.teacher_text, teacherName),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        text = stringResource(
                            id = R.string.lesson_text,
                            lesson.toString()
                        ),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Light
                    )
                    users?.size?.minus(1)?.let {
                        Text(
                            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_big)),
                            text = resources.getQuantityString(R.plurals.classroom_size_text, it, it),
                            fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                            fontWeight = FontWeight.Light
                        )
                    }
                }
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
            .padding(top = dimensionResource(id = R.dimen.padding_standard)),
        elevation = dimensionResource(id = R.dimen.elevation_standard),
    ) {
        ECollapsableLayout(
            collapsedContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_standard))
                            .size(dimensionResource(id = R.dimen.icon_size_medium)),
                        imageVector = Icons.Filled.Group,
                        tint = Gray,
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.padding_standard),
                                end = dimensionResource(id = R.dimen.padding_big),
                                top = dimensionResource(id = R.dimen.padding_big),
                                bottom = dimensionResource(id = R.dimen.padding_big),
                            ),
                        text = stringResource(id = R.string.students_button),
                        textAlign = TextAlign.Start,
                        fontSize = fontDimensionResource(id = R.dimen.font_size_standard),
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
                                        .height(dimensionResource(id = R.dimen.divider_height))
                                        .padding(
                                            start = dimensionResource(id = R.dimen.padding_standard),
                                            end = dimensionResource(id = R.dimen.padding_standard)
                                        )
                                        .align(Alignment.TopCenter)
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(dimensionResource(id = R.dimen.padding_standard))
                                        .align(Alignment.CenterStart),
                                    text = user.name.toString(),
                                    textAlign = TextAlign.Start,
                                    fontSize = fontDimensionResource(id = R.dimen.font_size_standard),
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    modifier = Modifier
                                        .size(dimensionResource(id = R.dimen.arrow_icon_size))
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
                    modifier = Modifier.size(dimensionResource(id = R.dimen.arrow_icon_size)),
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
    val positiveButton = DialogButton(stringResource(id = R.string.assign_button)) {
        onClick.invoke(inputState)
    }
    val negativeButton = DialogButton(stringResource(id = R.string.cancel_button), onClick = onDismiss)

    EDialog(
        title = stringResource(id = R.string.assign_user_dialog_title),
        onDismiss = onDismiss,
        positiveButton = positiveButton,
        negativeButton = negativeButton
    ) {
        ETextField(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_standard),
                end = dimensionResource(id = R.dimen.padding_standard)
            ),
            label = stringResource(id = R.string.email_hint),
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