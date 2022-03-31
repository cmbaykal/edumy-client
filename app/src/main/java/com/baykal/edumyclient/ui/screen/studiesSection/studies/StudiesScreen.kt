package com.baykal.edumyclient.ui.screen.studiesSection.studies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.EList
import com.baykal.edumyclient.base.component.ETabRow
import com.baykal.edumyclient.data.model.classroom.Lesson
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.component.StudyCard
import com.baykal.edumyclient.ui.screen.studiesSection.sendStudy.SendStudyRoute
import kotlinx.coroutines.launch

@Composable
fun StudiesScreen(viewModel: StudiesViewModel) {
//    LineChart(
//        modifier = Modifier
//            .height(300.dp)
//            .fillMaxWidth()
//            .padding(10.dp)
//    )

    val coroutineScope = rememberCoroutineScope()
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()

    val lessonItems = mutableListOf("All")
    enumValues<Lesson>().forEach {
        lessonItems.add(it.lessonName)
    }

    with(viewState) {
        LaunchedEffect(studies) {
            if (studies == null)
                viewModel.fetchData()
        }

        Scaffold(
            floatingActionButton = {
                if (userRole == UserRole.Student) {
                    EFab(onClick = {
                        viewModel.navigate(SendStudyRoute.route)
                    })
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                ETabRow(data = lessonItems, onSelect = {
                    viewModel.filterStudies(it)
                    coroutineScope.launch {
                        scrollState.scrollToItem(0)
                    }
                })
                studies?.let {
                    EList(
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 10.dp
                        ),
                        scrollState = scrollState,
                        swipeRefresh = true,
                        onRefresh = viewModel::fetchStudies,
                        items = it
                    ) { item ->
                        StudyCard(
                            modifier = Modifier.padding(10.dp),
                            study = item
                        )
                    }
                }
            }
        }
    }
}