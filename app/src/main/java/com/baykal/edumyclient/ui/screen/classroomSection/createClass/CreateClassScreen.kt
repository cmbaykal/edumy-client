package com.baykal.edumyclient.ui.screen.classroomSection.createClass

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.EDropDown
import com.baykal.edumyclient.base.component.ETextField
import com.baykal.edumyclient.data.model.classroom.Lesson

@Composable
fun CreateClassScreen(
    viewModel: CreateClassViewModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ETextField(
            label = "Class Name",
            onChange = viewModel::setClassName,
            success = { it.length in 3..30 },
            imeAction = ImeAction.Next
        )

        val items = mutableListOf<String>()
        enumValues<Lesson>().forEach {
            items.add(it.lessonName)
        }
        EDropDown(
            label = "Lesson",
            items = items,
            onChange = viewModel::setClassLesson
        )
        EButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(40.dp),
            text = "Create Class"
        ) {
            viewModel.createClass()
        }
    }
}