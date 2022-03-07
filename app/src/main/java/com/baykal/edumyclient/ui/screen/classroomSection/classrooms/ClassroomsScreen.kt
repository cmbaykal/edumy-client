package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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

@Composable
fun ClassroomsScreen(
    viewModel: ClassroomsViewModel? = null
) {
    val viewState = viewModel?.uiValue

    Scaffold(
        floatingActionButton = {
            EFab(onClick = {
                viewModel?.controller?.navigateToRoute(CreateClassRoute.route)
            })
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
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
                LazyColumn {
                    items(viewState?.classrooms ?: emptyList()) { classroom ->
                        ClassroomComponent(classroom) {
                            viewModel?.controller?.navigateToRoute(ClassroomRoute.route)
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
            .padding(8.dp)
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
    ) {
        Box {
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
                            text = "Mevcut : " + it.size.toString(),
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

