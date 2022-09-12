package com.baykal.edumyclient.ui.screen.meetingSection.meetings

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.UserClassroomsUseCase
import com.baykal.edumyclient.data.domain.meeting.UserMeetingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MeetingsViewModel @Inject constructor(
    private val session: EdumySession,
    private val userClassroomsUseCase: UserClassroomsUseCase,
    private val userMeetingsUseCase: UserMeetingsUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(MeetingsState())
    val uiState = _uiState.asStateFlow()

    fun getClassrooms() {
        session.withUserId { id ->
            userClassroomsUseCase.observe(id)
                .collectData { classList ->
                    classList?.let { list ->
                        _uiState.update { it.copy(classrooms = list) }
                    }
                }
        }
    }

    fun getMeetings() {
        session.withUser { user ->
            _uiState.update { it.copy(userRole = user.role) }
            user.id?.let { userId ->
                userMeetingsUseCase.observe(userId).collectData { response ->
                    _uiState.update { it.copy(meetings = response) }
                }
            }
        }
    }
}