package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.EList
import com.baykal.edumyclient.base.component.ETabRow
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.data.model.classroom.Lesson
import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.screen.questionSection.askquestion.AskQuestionRoute
import com.baykal.edumyclient.ui.screen.questionSection.questionDetail.QuestionDetailRoute
import kotlinx.coroutines.launch

@Composable
fun QuestionsScreen(
    viewModel: QuestionsViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()

    val lessonItems = mutableListOf("All")
    enumValues<Lesson>().forEach {
        lessonItems.add(it.lessonName)
    }

    with(viewState) {
        LaunchedEffect(this.questions) {
            if (questions == null)
                viewModel.fetchData()
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
                questions?.let {
                    EList(
                        scrollState = scrollState,
                        swipeRefresh = true,
                        onRefresh = viewModel::fetchQuestions,
                        loadMore = isMoreData,
                        onLoadMore = viewModel::getQuestions,
                        endContent = { EndComponent() },
                        items = it
                    ) { item ->
                        QuestionComponent(question = item) {
                            item.id?.let { questionId ->
                                viewModel.navigate(QuestionDetailRoute.get(questionId))
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
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clickable { onClick.invoke() }
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
                    Text(
                        text = question.date.toString(),
                        fontSize = 12.sp
                    )
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
fun EndComponent() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 14.dp,
                bottom = 14.dp
            ),
        text = "No more questions",
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
}