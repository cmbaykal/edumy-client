package com.baykal.edumyclient.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.EIconButton
import com.baykal.edumyclient.base.component.EImage
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.user.response.User

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
fun QuestionCard(
    modifier: Modifier = Modifier,
    question: Question,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = question.date.toString(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Column {
                Text(
                    text = question.lesson.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    text = question.description.toString(),
                    fontSize = 14.sp
                )
                question.image?.let { image ->
                    EImage(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(top = 12.dp),
                        file = image
                    )
                }
            }
            EButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                shape = RoundedCornerShape(50.dp),
                text = "Write Answer",
                textSize = 12.sp
            ) {
                question.id?.let {
                    onClick.invoke(it)
                }
            }
        }
    }
}