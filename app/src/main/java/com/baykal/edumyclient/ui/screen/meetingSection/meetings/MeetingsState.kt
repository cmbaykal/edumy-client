package com.baykal.edumyclient.ui.screen.meetingSection.meetings

import com.baykal.edumyclient.data.model.meeting.response.Meeting
import com.baykal.edumyclient.data.model.user.response.UserRole

data class MeetingsState(
    val userRole: UserRole? = null,
    val meetings: MutableList<Meeting>? = null
)