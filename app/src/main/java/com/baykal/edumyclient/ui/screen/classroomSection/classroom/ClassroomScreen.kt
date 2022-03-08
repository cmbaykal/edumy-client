package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.ECollapsableLayout
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.GrayLight
import com.baykal.edumyclient.base.ui.theme.Orange

@Composable
fun ClassroomScreen(
    viewModel: ClassroomViewModel? = null
) {
    val viewState = viewModel?.uiState?.collectAsState()

    viewState?.value?.classroom?.also {
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
                text = "Size - ${it.users?.size}",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                elevation = 4.dp
            ) {
                ECollapsableLayout(
                    collapsedContent = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 10.dp,
                                    end = 20.dp,
                                    top = 20.dp,
                                    bottom = 20.dp,
                                ),
                            text = "Users",
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    expandedContent = {
                        Column {
                            it.users?.let { users ->
                                users.forEach { user ->
                                    Box(
                                        modifier = Modifier.clickable {
                                            TODO("User Profile Navigation")
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
            Card(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                elevation = 4.dp
            ) {
                Box(
                    modifier = Modifier.clickable {
                        TODO("Classroom Questions Navigation")
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 10.dp,
                                end = 20.dp,
                                top = 20.dp,
                                bottom = 20.dp,
                            ),
                        text = "Questions",
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
            Card(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                elevation = 4.dp
            ) {
                Box(
                    modifier = Modifier.clickable {
                        TODO("Classroom Performances Navigation")
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 10.dp,
                                end = 20.dp,
                                top = 20.dp,
                                bottom = 20.dp,
                            ),
                        text = "Performances",
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
    } ?: run {
        viewModel?.getClassroomInformation()
    }
}

@Preview
@Composable
fun ClassroomScreenPreview() {
    ClassroomScreen()
}