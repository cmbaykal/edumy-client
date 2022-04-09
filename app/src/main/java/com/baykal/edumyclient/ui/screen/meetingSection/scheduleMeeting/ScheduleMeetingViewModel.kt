package com.baykal.edumyclient.ui.screen.meetingSection.scheduleMeeting

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.UserClassroomsUseCase
import com.baykal.edumyclient.data.domain.meeting.ScheduleMeetingUseCase
import com.baykal.edumyclient.data.model.meeting.request.MeetingBody
import com.baykal.edumyclient.ui.screen.meetingSection.meetings.MeetingsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScheduleMeetingViewModel @Inject constructor(
    private val session: EdumySession,
    private val userClassroomsUseCase: UserClassroomsUseCase,
    private val scheduleMeetingUseCase: ScheduleMeetingUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ScheduleMeetingState())
    val uiState = _uiState.asStateFlow()

    fun fetchData() {
        session.withUserId { id ->
            userClassroomsUseCase.observe(id)
                .collectData { classList ->
                    classList?.let { list ->
                        _uiState.update { it.copy(classrooms = list) }
                    }
                }
        }
    }

    fun setClassroom(state: InputState) {
        val item = uiState.value.classrooms?.find { it.name == state.text }
        _uiState.update { it.copy(selectedClassroom = item) }
    }

    fun setDescription(state: InputState) {
        _uiState.update { it.copy(description = state) }
    }

    fun setDuration(state: InputState) {
        _uiState.update { it.copy(duration = state) }
    }

    fun setDate(state: InputState) {
        _uiState.update { it.copy(date = state) }
    }

    fun scheduleMeeting() {
        with(uiState.value) {
            if (isFormValid) {
                session.withUserId { userId ->
                    val meetingBody = MeetingBody(
                        classId = selectedClassroom?.id.toString(),
                        creatorId = userId,
                        description = description.text,
                        duration = duration.text.toInt(),
                        date = date.text
                    )
                    scheduleMeetingUseCase.observe(meetingBody)
                        .collectData {
                            controller.showDialog(
                                "Meeting is Set",
                                "The meeting has been set up successfully.",
                            ) {
                                navigate(MeetingsRoute.route, true)
                            }
                        }
                }
            }
        }
    }
}