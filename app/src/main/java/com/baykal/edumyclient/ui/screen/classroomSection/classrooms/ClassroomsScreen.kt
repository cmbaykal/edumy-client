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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.ESearchView
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.ui.screen.classroomSection.classroom.ClassroomRoute
import com.baykal.edumyclient.ui.screen.classroomSection.createClass.CreateClassRoute
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ClassroomsScreen(
    viewModel: ClassroomsViewModel? = null
) {
    val viewState = viewModel?.uiValue
    val swipeRefreshState = rememberSwipeRefreshState(false)

    Scaffold(
        floatingActionButton = {
            EFab(onClick = {
                viewModel?.navigate(CreateClassRoute.route)
            })
        }
    ) {
        Column {
            ESearchView(
                modifier = Modifier.fillMaxWidth(),
                label = "Search Classroom",
                value = viewState?.searchText ?: "",
                onChange = {
                    viewModel?.filterClasses(it)
                },
                onAction = { viewModel?.filterClasses() }
            )
            SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel?.getClassrooms() }) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(viewState?.classrooms ?: emptyList()) { classroom ->
                        ClassroomComponent(classroom) {
                            classroom.id?.let {
                                viewModel?.navigate(ClassroomRoute.get(it))
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
                    classroom.users?.let {
                        Text(
                            text = "Size : " + it.size.toString(),
                            fontSize = 12.sp
                        )
                    }
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

@Preview
@Composable
fun ClassroomScreenPreview() {
    ClassroomsScreen()
}

