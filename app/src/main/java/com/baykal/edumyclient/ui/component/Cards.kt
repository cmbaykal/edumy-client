package com.baykal.edumyclient.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.EIconButton
import com.baykal.edumyclient.base.component.EImage
import com.baykal.edumyclient.base.extension.fontDimensionResource
import com.baykal.edumyclient.base.extension.string
import com.baykal.edumyclient.base.extension.stringWithoutTime
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.meeting.response.Meeting
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.study.response.Study
import com.baykal.edumyclient.data.model.user.response.User
import java.util.*

@Composable
fun ClassroomListCard(
    modifier: Modifier = Modifier,
    classroom: Classroom,
    onClick: () -> Unit
) {
    val resources = LocalContext.current.resources

    with(classroom) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_standard),
                    end = dimensionResource(id = R.dimen.padding_standard),
                    top = dimensionResource(id = R.dimen.padding_medium),
                    bottom = dimensionResource(id = R.dimen.padding_medium)
                )
                .then(modifier),
            elevation = dimensionResource(id = R.dimen.elevation_standard),
        ) {
            Box(
                modifier = Modifier
                    .clickable { onClick.invoke() }
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_standard),
                        bottom = dimensionResource(id = R.dimen.padding_standard)
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterStart),
                ) {
                    Icon(
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard)),
                        imageVector = Icons.Filled.Class,
                        tint = Gray,
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard)),
                    ) {
                        Text(
                            text = "$name - $lesson",
                            fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                            fontWeight = FontWeight.Bold
                        )
                        users?.size?.minus(1)?.let {
                            Text(
                                text = resources.getQuantityString(R.plurals.classroom_size_text, it, it),
                                fontSize = fontDimensionResource(id = R.dimen.font_size_small)
                            )
                        }
                    }
                }
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = dimensionResource(id = R.dimen.padding_standard)),
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    tint = Gray,
                    contentDescription = ""
                )
            }
        }
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
                start = dimensionResource(id = R.dimen.padding_huge),
                end = dimensionResource(id = R.dimen.padding_huge),
            )
            .then(modifier),
        elevation = dimensionResource(id = R.dimen.elevation_big)
    ) {
        Box {
            EIconButton(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .align(Alignment.TopEnd),
                icon = Icons.Filled.Edit,
                iconSize = dimensionResource(id = R.dimen.icon_size_medium),
                onClick = onEditClick
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_big))
                        .size(dimensionResource(id = R.dimen.profile_card_icon_size)),
                    imageVector = Icons.Filled.AccountCircle,
                    tint = Gray,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_standard)),
                    text = user.name.toString(),
                    fontSize = fontDimensionResource(id = R.dimen.font_size_big),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(
                        bottom = if (user.bio != null) 0.dp else dimensionResource(id = R.dimen.padding_big)
                    ),
                    text = user.role.toString(),
                    fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                    fontWeight = FontWeight.Light
                )
                user.bio?.let {
                    Text(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.padding_standard),
                            bottom = dimensionResource(id = R.dimen.padding_big)
                        ),
                        text = it,
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileCardCompact(
    modifier: Modifier = Modifier,
    user: User?,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        elevation = dimensionResource(id = R.dimen.elevation_standard)
    ) {
        Box(
            modifier = Modifier.clickable {
                user?.id?.let {
                    onClick.invoke(it)
                }
            }
        ) {
            Row(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.profile_card_compact_icon_size)),
                    imageVector = Icons.Filled.AccountCircle,
                    tint = Gray,
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard))
                ) {
                    Text(
                        text = user?.name.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = user?.role.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                        fontWeight = FontWeight.Light
                    )
                }
            }
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

@Composable
fun QuestionListCard(
    modifier: Modifier = Modifier,
    question: Question,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding_standard),
                end = dimensionResource(id = R.dimen.padding_standard),
                top = dimensionResource(id = R.dimen.padding_medium),
                bottom = dimensionResource(id = R.dimen.padding_medium)
            )
            .then(modifier),
        elevation = dimensionResource(id = R.dimen.elevation_standard),
    ) {
        Box(
            modifier = Modifier
                .clickable { onClick.invoke() }
                .padding(dimensionResource(id = R.dimen.padding_standard))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterStart),
            ) {
                Icon(
                    imageVector = Icons.Filled.QuestionAnswer,
                    tint = Gray,
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard))
                ) {
                    Text(
                        text = question.description.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    question.date?.let {
                        Text(
                            text = it.string,
                            fontSize = fontDimensionResource(id = R.dimen.font_size_small)
                        )
                    }
                }
            }
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.Filled.KeyboardArrowRight,
                tint = Gray,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun QuestionCard(
    modifier: Modifier = Modifier,
    question: Question,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        elevation = dimensionResource(id = R.dimen.elevation_standard)
    ) {
        Box(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_standard))
        ) {
            question.date?.let {
                Text(
                    modifier = Modifier.align(Alignment.TopEnd),
                    text = it.string,
                    fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                    fontWeight = FontWeight.Light
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = question.lesson.toString(),
                    fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_standard)),
                    text = question.description.toString(),
                    fontSize = fontDimensionResource(id = R.dimen.font_size_medium)
                )
                question.image?.let { image ->
                    EImage(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.image_height_small))
                            .padding(top = dimensionResource(id = R.dimen.padding_standard)),
                        file = image
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerCard(
    modifier: Modifier = Modifier,
    userId: String,
    answer: Answer,
    onProfileClick: () -> Unit = {},
    onUpVote: () -> Unit = {},
    onDownVote: () -> Unit = {},
    onImageClick: (String) -> Unit = {},
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        elevation = dimensionResource(id = R.dimen.elevation_standard)
    ) {
        Column(modifier = Modifier
            .clickable {
                answer.questionId?.let {
                    onClick.invoke(it)
                }
            }
            .padding(dimensionResource(id = R.dimen.padding_standard))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                answer.user?.let { user ->
                    Text(
                        modifier = Modifier
                            .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                            .clickable {
                                onProfileClick.invoke()
                            },
                        text = user.name.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Bold
                    )
                }
                answer.date?.let {
                    Text(
                        text = it.string,
                        fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                        fontWeight = FontWeight.Light
                    )
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = answer.description.toString(),
                fontSize = fontDimensionResource(id = R.dimen.font_size_small)
            )
            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_medium))
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    answer.image?.let {
                        EIconButton(
                            size = dimensionResource(id = R.dimen.answer_card_button_size),
                            iconSize = dimensionResource(id = R.dimen.icon_size_medium),
                            icon = Icons.Filled.Photo
                        ) {
                            onImageClick.invoke(it)
                        }
                    }
                    answer.video?.let {
                        EIconButton(
                            size = dimensionResource(id = R.dimen.answer_card_button_size),
                            iconSize = dimensionResource(id = R.dimen.icon_size_medium),
                            icon = Icons.Filled.Videocam
                        ) {
                            onUpVote.invoke()
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    EIconButton(
                        size = dimensionResource(id = R.dimen.answer_card_button_size),
                        iconSize = dimensionResource(id = R.dimen.icon_size_medium),
                        icon = if (answer.downVote?.contains(userId) == true) Icons.Filled.ThumbDownAlt else Icons.Filled.ThumbDownOffAlt,
                    ) {
                        answer.id?.let {
                            onDownVote.invoke()
                        }
                    }
                    if (!answer.downVote.isNullOrEmpty()) {
                        Text(
                            text = answer.downVote?.size.toString(),
                            fontSize = fontDimensionResource(id = R.dimen.font_size_small)
                        )
                    }
                    EIconButton(
                        size = dimensionResource(id = R.dimen.answer_card_button_size),
                        iconSize = dimensionResource(id = R.dimen.icon_size_medium),
                        icon = if (answer.upVote?.contains(userId) == true) Icons.Filled.ThumbUpAlt else Icons.Filled.ThumbUpOffAlt,
                    ) {
                        answer.id?.let {
                            onUpVote.invoke()
                        }
                    }
                    if (!answer.upVote.isNullOrEmpty()) {
                        Text(
                            text = answer.upVote?.size.toString(),
                            fontSize = fontDimensionResource(id = R.dimen.font_size_small)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudyCard(
    modifier: Modifier = Modifier,
    study: Study
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        elevation = dimensionResource(id = R.dimen.elevation_standard)
    ) {
        with(study) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_standard))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.lesson_text, lesson.toString()),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Bold
                    )
                    date?.let {
                        Text(
                            text = it.stringWithoutTime,
                            fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        tint = Gray,
                        contentDescription = "Icon Button",
                        modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_medium))
                    )
                    Text(
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium)),
                        text = correctA.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_standard))
                            .size(dimensionResource(id = R.dimen.icon_size_medium)),
                        imageVector = Icons.Default.Close,
                        tint = Gray,
                        contentDescription = "Icon Button"
                    )
                    Text(
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium)),
                        text = wrongA.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_standard))
                            .size(dimensionResource(id = R.dimen.icon_size_medium)),
                        imageVector = Icons.Default.CheckBoxOutlineBlank,
                        tint = Gray,
                        contentDescription = "Icon Button",
                    )
                    Text(
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium)),
                        text = emptyQ.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                    )
                }
            }
        }
    }
}

@Composable
fun MeetingCard(
    modifier: Modifier = Modifier,
    meeting: Meeting,
    onClick: (Meeting) -> Unit
) {
    with(meeting) {
        val status = date?.time ?: 0 > Date().time
        val meetingStatus = if (status) R.string.active_text else R.string.passive_text

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            elevation = dimensionResource(id = R.dimen.elevation_standard)
        ) {
            Column(
                modifier = Modifier
                    .clickable(onClick = { onClick(meeting) }, enabled = status)
                    .padding(dimensionResource(id = R.dimen.padding_standard))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${classroom?.name} - ${classroom?.lesson}",
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Bold
                    )
                    date?.let {
                        Text(
                            text = it.string,
                            fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.teacher_text, user?.name.toString()),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(id = R.string.duration_text, duration),
                        fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                        fontWeight = FontWeight.Light
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(id = R.string.status_text, meetingStatus),
                    fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}