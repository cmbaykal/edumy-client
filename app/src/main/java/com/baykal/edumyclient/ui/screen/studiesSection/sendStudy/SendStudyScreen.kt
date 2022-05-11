package com.baykal.edumyclient.ui.screen.studiesSection.sendStudy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.baykal.edumyclient.R
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
                .padding(
                    start = dimensionResource(id = R.dimen.padding_huge),
                    end = dimensionResource(id = R.dimen.padding_huge),
                    bottom = dimensionResource(id = R.dimen.padding_huge)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            EDropDown(
                label = stringResource(R.string.lesson_hint),
                items = lessonItems,
                selected = lesson.text,
                onChange = viewModel::setLesson
            )
            ETextField(
                label = stringResource(id = R.string.correct_answers_text),
                onChange = viewModel::setCorrectAnswers,
                success = { it.isNotEmpty() },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            ETextField(
                label = stringResource(id = R.string.wrong_answers_text),
                onChange = viewModel::setWrongAnswers,
                success = { it.isNotEmpty() },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            ETextField(
                label = stringResource(id = R.string.empty_questions_text),
                onChange = viewModel::setEmptyQuestions,
                success = { it.isNotEmpty() },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                onAction = { viewModel.sendStudy() }
            )
            EButton(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_standard))
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.button_height_standard)),
                text = stringResource(id = R.string.send_study_button)
            ) {
                viewModel.sendStudy()
            }
        }
    }
}