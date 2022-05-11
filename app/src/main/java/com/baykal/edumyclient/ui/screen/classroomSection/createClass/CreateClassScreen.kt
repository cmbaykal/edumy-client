package com.baykal.edumyclient.ui.screen.classroomSection.createClass

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.baykal.edumyclient.R
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
            .padding(
                start = dimensionResource(id = R.dimen.padding_huge),
                end = dimensionResource(id = R.dimen.padding_huge),
                bottom = dimensionResource(id = R.dimen.padding_huge)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ETextField(
            label = stringResource(id = R.string.classroom_name_hint),
            onChange = viewModel::setClassName,
            success = { it.length in 3..30 },
            imeAction = ImeAction.Next
        )

        val items = mutableListOf<String>()
        enumValues<Lesson>().forEach {
            items.add(it.lessonName)
        }
        EDropDown(
            label = stringResource(id = R.string.lesson_hint),
            items = items,
            onChange = viewModel::setClassLesson
        )
        EButton(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_standard))
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.button_height_standard)),
            text = stringResource(id = R.string.create_classroom_button)
        ) {
            viewModel.createClass()
        }
    }
}