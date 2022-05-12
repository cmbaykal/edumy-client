package com.baykal.edumyclient.ui.screen.studiesSection.studies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.baykal.edumyclient.R
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

    val lessonItems = mutableListOf(stringResource(id = R.string.all_text))
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
                    val chartData = listOf(
                        ChartData(
                            identifier = stringResource(id = R.string.correct_answers_text),
                            point = studies?.sumOf { it.correctA.toString().toInt() } ?: 0,
                            color = Orange
                        ),
                        ChartData(
                            identifier = stringResource(id = R.string.wrong_answers_text),
                            point = studies?.sumOf { it.wrongA.toString().toInt() } ?: 0,
                            color = Red
                        ),
                        ChartData(
                            identifier = stringResource(id = R.string.empty_questions_text),
                            point = studies?.sumOf { it.emptyQ.toString().toInt() } ?: 0,
                            color = Purple
                        )
                    )
                AnimatedVisibility(visible = !studies.isNullOrEmpty()) {
                    PieChart(
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.padding_big))
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.pie_chart_height)),
                        data = chartData
                    )
                }
                ETabRow(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_big)),
                    data = lessonItems,
                    onSelect = { selected ->
                        viewModel.filterStudies(selected)
                        coroutineScope.launch {
                            scrollState.scrollToItem(0)
                        }
                    })
                EList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_big),
                            end = dimensionResource(id = R.dimen.padding_big),
                            top = dimensionResource(id = R.dimen.padding_standard)
                        ),
                    scrollState = scrollState,
                    swipeRefreshSettings = ListSwipeRefreshSettings(enabled = true, onRefresh = viewModel::fetchStudies),
                    listItems = studies
                ) { item ->
                    StudyCard(
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_standard),
                            end = dimensionResource(id = R.dimen.padding_standard),
                            top = dimensionResource(id = R.dimen.padding_medium),
                            bottom = dimensionResource(id = R.dimen.padding_medium)
                        ),
                        study = item
                    )
                }
            }
        }
    }
}