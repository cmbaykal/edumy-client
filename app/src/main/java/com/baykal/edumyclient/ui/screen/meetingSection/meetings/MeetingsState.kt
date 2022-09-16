package com.baykal.edumyclient.ui.screen.meetingSection.meetings

import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.model.meeting.response.Meeting
import com.baykal.edumyclient.data.model.user.response.User

data class MeetingsState(
    val user: User? = null,
    val classrooms: MutableList<Classroom>? = null,
    val meetings: MutableList<Meeting>? = null
)