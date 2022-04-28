package com.baykal.edumyclient.ui.screen.meetingSection.scheduleMeeting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.extension.toJson

@Composable
fun ScheduleMeetingScreen(
    viewModel: ScheduleMeetingViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val dateDialogState = remember { mutableStateOf(false) }

    val durationItems = mutableListOf(
        "15", "30", "45", "60"
    )

    with(viewState) {
        LaunchedEffect(classrooms) {
            if (classrooms == null)
                viewModel.fetchData()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(dimensionResource(id = R.dimen.padding_huge)),
            verticalArrangement = Arrangement.Center
        ) {
            classrooms?.apply {
                val items = this.map { it.name.toString() }.toMutableList()
                EDropDown(
                    label = stringResource(R.string.classroom_hint),
                    items = items,
                    selected = selectedClassroom?.name,
                    onChange = viewModel::setClassroom
                )
                ETextField(
                    label = stringResource(id = R.string.description_hint),
                    value = description.text,
                    onChange = viewModel::setDescription,
                    success = { it.isNotBlank() },
                    maxLines = 3
                )
                EDialogField(
                    label = stringResource(id = R.string.meeting_date_hint),
                    value = date.text,
                    dialogState = dateDialogState,
                    onClick = {
                        dateDialogState.value = true
                    },
                    onDismiss = {
                        dateDialogState.value = false
                    }
                ) {
                    EDatePicker(
                        timeEnabled = true,
                        onChange = { d ->
                            viewModel.setDate(InputState(text = d.toJson, isSuccess = true))
                        }
                    )
                }
                EDropDown(
                    label = stringResource(id = R.string.meeting_duration_hint),
                    itemSuffix = stringResource(id = R.string.meeting_duration_suffix_text),
                    items = durationItems,
                    selected = duration.text,
                    onChange = viewModel::setDuration
                )
                EButton(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_standard))
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.button_height_standard)),
                    text = stringResource(id = R.string.schedule_meeting_button)
                ) {
                    viewModel.scheduleMeeting()
                }
            }
        }
    }
}