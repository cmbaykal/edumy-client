package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.extension.fontDimensionResource
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

    val lessonItems = mutableListOf(stringResource(id = R.string.all_text))
    enumValues<Lesson>().forEach {
        lessonItems.add(it.lessonName)
    }

    with(viewState) {
        LaunchedEffect(questions) {
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
                EList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_big),
                            end = dimensionResource(id = R.dimen.padding_big),
                            top = dimensionResource(id = R.dimen.padding_standard)
                        ),
                    scrollState = scrollState,
                    swipeRefreshSettings = ListSwipeRefreshSettings(enabled = true, onRefresh = viewModel::fetchQuestions),
                    loadMoreSettings = ListLoadMoreSettings(
                        enabled = isMoreData,
                        onLoadMore = viewModel::getQuestions,
                        loadMoreContent = {},
                        endContent = { EndComponent() },
                    ),
                    listItems = questions
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

@Composable
fun EndComponent() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.padding_big),
                bottom = dimensionResource(id = R.dimen.padding_big)
            ),
        text = stringResource(id = R.string.no_more_questions_text),
        textAlign = TextAlign.Center,
        fontSize = fontDimensionResource(id = R.dimen.font_size_medium),
        fontWeight = FontWeight.Bold
    )
}