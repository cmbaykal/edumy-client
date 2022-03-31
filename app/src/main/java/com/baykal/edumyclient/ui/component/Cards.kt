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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EIconButton
import com.baykal.edumyclient.base.component.EImage
import com.baykal.edumyclient.base.extension.string
import com.baykal.edumyclient.base.extension.stringWithoutTime
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.study.response.Study
import com.baykal.edumyclient.data.model.user.response.User

@Composable
fun ClassroomListCard(
    modifier: Modifier = Modifier,
    classroom: Classroom,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 6.dp,
                bottom = 6.dp
            )
            .then(modifier),
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier.clickable { onClick.invoke() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterStart),
            ) {
                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    imageVector = Icons.Filled.Class,
                    tint = Gray,
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "${classroom.name.toString()} - ${classroom.lesson}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = classroom.classSize,
                        fontSize = 12.sp
                    )
                }
            }
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp),
                imageVector = Icons.Filled.KeyboardArrowRight,
                tint = Gray,
                contentDescription = ""
            )
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
                start = 40.dp,
                end = 40.dp
            )
            .then(
                modifier
            ),
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
                    modifier = Modifier.padding(
                        bottom = if (user.bio != null) 0.dp else 20.dp
                    ),
                    text = user.role.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
                user.bio?.let {
                    Text(
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 20.dp
                        ),
                        text = it,
                        fontSize = 14.sp,
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
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier.clickable {
                user?.id?.let {
                    onClick.invoke(it)
                }
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    tint = Gray,
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = user?.name.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = user?.role.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
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
                start = 10.dp,
                end = 10.dp,
                top = 6.dp,
                bottom = 6.dp
            )
            .then(modifier),
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier
                .clickable { onClick.invoke() }
                .padding(8.dp)
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
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = question.description.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    question.date?.let {
                        Text(
                            text = it.string,
                            fontSize = 12.sp
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
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            question.date?.let {
                Text(
                    modifier = Modifier.align(Alignment.TopEnd),
                    text = it.string,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = question.lesson.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = question.description.toString(),
                    fontSize = 14.sp
                )
                question.image?.let { image ->
                    EImage(
                        modifier = Modifier
                            .height(60.dp)
                            .padding(top = 8.dp),
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
        elevation = 4.dp
    ) {
        Column(modifier = Modifier
            .clickable {
                answer.questionId?.let {
                    onClick.invoke(it)
                }
            }
            .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                answer.user?.let { user ->
                    Text(
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .clickable {
                                onProfileClick.invoke()
                            },
                        text = user.name.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                answer.date?.let {
                    Text(
                        text = it.string,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = answer.description.toString(),
                fontSize = 12.sp
            )
            Row(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    answer.image?.let {
                        EIconButton(
                            size = 35.dp,
                            iconSize = 20.dp,
                            icon = Icons.Filled.Photo
                        ) {
                            onImageClick.invoke(it)
                        }
                    }
                    answer.video?.let {
                        EIconButton(
                            size = 35.dp,
                            iconSize = 20.dp,
                            icon = Icons.Filled.Videocam
                        ) {
                            onUpVote.invoke()
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    EIconButton(
                        size = 35.dp,
                        iconSize = 20.dp,
                        icon = if (answer.downVote?.contains(userId) == true) Icons.Filled.ThumbDownAlt else Icons.Filled.ThumbDownOffAlt,
                    ) {
                        answer.id?.let {
                            onDownVote.invoke()
                        }
                    }
                    if (!answer.downVote.isNullOrEmpty()) {
                        Text(
                            text = answer.downVote?.size.toString(),
                            fontSize = 12.sp
                        )
                    }
                    EIconButton(
                        size = 35.dp,
                        iconSize = 20.dp,
                        icon = if (answer.upVote?.contains(userId) == true) Icons.Filled.ThumbUpAlt else Icons.Filled.ThumbUpOffAlt,
                    ) {
                        answer.id?.let {
                            onUpVote.invoke()
                        }
                    }
                    if (!answer.upVote.isNullOrEmpty()) {
                        Text(
                            text = answer.upVote?.size.toString(),
                            fontSize = 12.sp
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
        elevation = 4.dp
    ) {
        with(study) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Lesson - $lesson",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    date?.let {
                        Text(
                            text = it.stringWithoutTime,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        tint = Gray,
                        contentDescription = "Icon Button",
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = correctA.toString(),
                        fontSize = 12.sp,
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(18.dp),
                        imageVector = Icons.Default.Close,
                        tint = Gray,
                        contentDescription = "Icon Button"
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = wrongA.toString(),
                        fontSize = 12.sp,
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(18.dp),
                        imageVector = Icons.Default.CheckBoxOutlineBlank,
                        tint = Gray,
                        contentDescription = "Icon Button",
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = emptyA.toString(),
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}