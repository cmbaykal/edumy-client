package com.baykal.edumyclient.ui.screen.studiesSection.studies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.ui.theme.Orange
import com.baykal.edumyclient.base.ui.theme.Purple
import com.baykal.edumyclient.base.ui.theme.Red
import com.baykal.edumyclient.data.model.classroom.Lesson
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.component.StudyCard
import com.baykal.edumyclient.ui.screen.studiesSection.sendStudy.SendStudyRoute
import kotlinx.coroutines.launch

@Composable
fun StudiesScreen(viewModel: StudiesViewModel) {

    val viewState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
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
                modifier = Modifier
            ) {
                if (!studies.isNullOrEmpty()) {
                    val chartData = listOf(
                        ChartData(
                            identifier = "Correct Answers",
                            point = studies.sumOf { it.correctA.toString().toInt() },
                            color = Orange
                        ),
                        ChartData(
                            "Wrong Answers",
                            point = studies.sumOf { it.wrongA.toString().toInt() },
                            color = Red
                        ),
                        ChartData(
                            "Empty Questions",
                            point = studies.sumOf { it.emptyQ.toString().toInt() },
                            color = Purple
                        )
                    )
                    PieChart(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                            .height(150.dp),
                        data = chartData
                    )
                    ETabRow(
                        modifier = Modifier.padding(top = 20.dp),
                        data = lessonItems,
                        onSelect = { selected ->
                            viewModel.filterStudies(selected)
                            coroutineScope.launch {
                                scrollState.scrollToItem(0)
                            }
                        })
                }
                EList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 10.dp
                        ),
                    scrollState = scrollState,
                    swipeRefresh = true,
                    onRefresh = viewModel::fetchStudies,
                    listItems = studies
                ) { item ->
                    StudyCard(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 6.dp,
                            bottom = 6.dp
                        ),
                        study = item
                    )
                }
            }
        }
    }
}