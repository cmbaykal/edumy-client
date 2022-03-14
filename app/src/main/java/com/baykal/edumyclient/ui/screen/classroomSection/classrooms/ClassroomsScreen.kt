package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.ESearchView
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.screen.classroomSection.classroomDetail.ClassroomDetailRoute
import com.baykal.edumyclient.ui.screen.classroomSection.createClass.CreateClassRoute
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ClassroomsScreen(
    viewModel: ClassroomsViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(false)

    with(viewState) {
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
                    modifier = Modifier.fillMaxWidth(),
                    label = "Search Classroom",
                    value = searchText,
                    onChange = viewModel::filterClasses,
                    onAction = { viewModel.filterClasses() }
                )
                SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.getClassrooms() }) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(classrooms) { classroom ->
                            ClassroomComponent(classroom) {
                                classroom.id?.let {
                                    viewModel.navigate(ClassroomDetailRoute.get(it))
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
fun ClassroomComponent(classroom: Classroom, onClick: () -> Unit) {
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
                    imageVector = Icons.Filled.Class,
                    tint = Gray,
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "${classroom.name.toString()} - ${classroom.lesson}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = classroom.classSize,
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

