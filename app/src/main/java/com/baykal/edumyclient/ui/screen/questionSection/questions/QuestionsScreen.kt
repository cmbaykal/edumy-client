package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.EList
import com.baykal.edumyclient.base.component.ETabRow
import com.baykal.edumyclient.data.model.classroom.Lesson
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.component.QuestionListCard
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
                        QuestionListCard(question = item) {
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