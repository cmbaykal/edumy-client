package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.EdumyTabRow
import com.baykal.edumyclient.data.model.classroom.Lesson
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.screen.questionSection.askquestion.AskQuestionRoute
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun QuestionsScreen(
    viewModel: QuestionsViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(false)

    val lessonItems = mutableListOf<String>()
    enumValues<Lesson>().forEach {
        lessonItems.add(it.lessonName)
    }

    with(viewState) {
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
                EdumyTabRow(data = lessonItems, onSelect = {
                    viewModel.filterQuestions(it)
                })
                SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.getQuestions() }) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
//                        items(classrooms) { classroom ->
//                            ClassroomComponent(classroom) {
//                                classroom.id?.let {
//                                    viewModel.navigate(ClassroomDetailRoute.get(it))
//                                }
//                            }
//                        }
                    }
                }
            }
        }
    }
}