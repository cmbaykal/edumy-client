package com.baykal.edumyclient.ui.screen.meetingSection.scheduleMeeting

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.data.model.classroom.response.Classroom

data class ScheduleMeetingState(
    val classrooms: MutableList<Classroom>? = null,
    val selectedClassroom: Classroom? = null,
    val description: InputState = InputState(),
    val duration: InputState = InputState(),
    val date: InputState = InputState()
) {
    val isFormValid get() = selectedClassroom != null && description.isSuccess && duration.isSuccess && date.isSuccess
}
