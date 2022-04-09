package com.baykal.edumyclient.ui.screen.meetingSection.scheduleMeeting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                .padding(40.dp),
            verticalArrangement = Arrangement.Center
        ) {
            classrooms?.apply {
                val items = this.map { it.name.toString() }.toMutableList()
                EDropDown(
                    label = "Classroom",
                    items = items,
                    selected = selectedClassroom?.name,
                    onChange = viewModel::setClassroom
                )
                ETextField(
                    label = "Description",
                    value = description.text,
                    onChange = viewModel::setDescription,
                    success = { it.isNotBlank() },
                    maxLines = 3
                )
                EDialogField(
                    label = "Meeting Date",
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
                    label = "Duration (Minutes)",
                    items = durationItems,
                    selected = duration.text,
                    onChange = viewModel::setDuration
                )
                EButton(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(40.dp),
                    text = "Schedule Meeting"
                ) {
                    viewModel.scheduleMeeting()
                }
            }
        }
    }
}