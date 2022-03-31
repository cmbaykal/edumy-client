package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.EList
import com.baykal.edumyclient.base.component.ESearchView
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.component.ClassroomListCard
import com.baykal.edumyclient.ui.screen.classroomSection.classroomDetail.ClassroomDetailRoute
import com.baykal.edumyclient.ui.screen.classroomSection.createClass.CreateClassRoute

@Composable
fun ClassroomsScreen(
    viewModel: ClassroomsViewModel
) {
    val viewState by viewModel.uiState.collectAsState()

    with(viewState) {
        LaunchedEffect(classrooms) {
            if (classrooms == null) {
                viewModel.getClassrooms()
            }
        }

        Scaffold(
            floatingActionButton = {
                if (userRole == UserRole.Teacher) {
                    EFab(onClick = {
                        viewModel.navigate(CreateClassRoute.route)
                    })
                }
            }
        ) {
            Column {
                ESearchView(
                    modifier = Modifier.padding(top = 10.dp),
                    label = "Search Classroom",
                    value = searchText,
                    onChange = viewModel::filterClasses,
                    onAction = { viewModel.filterClasses() }
                )
                classrooms?.let {
                    EList(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                        swipeRefresh = true,
                        onRefresh = viewModel::getClassrooms,
                        items = it,
                    ) { classroom ->
                        ClassroomListCard(classroom = classroom) {
                            classroom.id?.let { classId ->
                                viewModel.navigate(ClassroomDetailRoute.get(classId))
                            }
                        }
                    }
                }
            }
        }
    }
}
