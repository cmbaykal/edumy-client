package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.ETabRow
import com.baykal.edumyclient.base.extension.isScrolledToEnd
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.data.model.classroom.Lesson
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.screen.questionSection.askquestion.AskQuestionRoute
import com.baykal.edumyclient.ui.screen.questionSection.questionDetail.QuestionDetailRoute
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun QuestionsScreen(
    viewModel: QuestionsViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val viewState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val scrollState = rememberLazyListState()
    val scrollBottomState by remember {
        derivedStateOf {
            scrollState.isScrolledToEnd()
        }
    }

    val lessonItems = mutableListOf("All")
    enumValues<Lesson>().forEach {
        lessonItems.add(it.lessonName)
    }

    with(viewState) {
        LaunchedEffect(this.questions) {
            if (questions == null)
                viewModel.fetchData()
        }

        LaunchedEffect(scrollBottomState) {
            if (scrollBottomState) {
                viewModel.getQuestions()
            }
        }

        Scaffold(
            floatingActionButton = {
                if (userRole == UserRole.Student) {
                    EFab(onClick = {
                        viewModel.navigate(AskQuestionRoute.route)
                    })
                }
            }
        ) {
            Column {
                userRole?.let {
                    ETabRow(data = lessonItems, onSelect = {
                        viewModel.filterQuestions(it)
                        coroutineScope.launch {
                            scrollState.scrollToItem(0)
                        }
                    })
                }
                SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.fetchQuestions() }) {
                    questions?.let {
                        LazyColumn(
                            state = scrollState,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxSize()
                        ) {
                            items(it) { question ->
                                QuestionComponent(question = question) {
                                    viewModel.navigate(QuestionDetailRoute.route)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionComponent(
    question: Question,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
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
                    modifier = Modifier.padding(start = 10.dp),
                    imageVector = Icons.Filled.QuestionAnswer,
                    tint = Gray,
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "${question.description.toString()} - ${question.lesson}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = question.date.toString(),
                        fontSize = 12.sp
                    )
                }
            }
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp),
                imageVector = Icons.Filled.KeyboardArrowRight,
                tint = Gray,
                contentDescription = ""
            )
        }
    }
}