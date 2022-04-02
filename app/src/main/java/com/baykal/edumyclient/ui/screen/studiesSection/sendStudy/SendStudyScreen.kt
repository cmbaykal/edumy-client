package com.baykal.edumyclient.ui.screen.studiesSection.sendStudy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.EDropDown
import com.baykal.edumyclient.base.component.ETextField
import com.baykal.edumyclient.data.model.classroom.Lesson

@Composable
fun SendStudyScreen(
    viewModel: SendStudyViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val lessonItems = mutableListOf<String>()
    enumValues<Lesson>().forEach {
        lessonItems.add(it.lessonName)
    }

    with(viewState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(40.dp),
            verticalArrangement = Arrangement.Center
        ) {
            EDropDown(
                label = "Lesson",
                items = lessonItems,
                selected = lesson.text,
                onChange = viewModel::setLesson
            )
            ETextField(
                label = "Correct Answers",
                onChange = viewModel::setCorrectAnswers,
                success = { it.isNotEmpty() },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            ETextField(
                label = "Wrong Answers",
                onChange = viewModel::setWrongAnswers,
                success = { it.isNotEmpty() },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            ETextField(
                label = "Empty Answers",
                value = "0",
                onChange = viewModel::setEmptyQuestions,
                success = { it.isNotEmpty() },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                onAction = { viewModel.sendStudy() }
            )
            EButton(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                text = "Send Study"
            ) {
                viewModel.sendStudy()
            }
        }
    }
}