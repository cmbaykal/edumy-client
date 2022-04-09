package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.meeting.request.MeetingBody
import com.baykal.edumyclient.data.service.EdumyService
import javax.inject.Inject

class MeetingRepository @Inject constructor(
    private val service: EdumyService
) : BaseRepository() {

    fun scheduleMeeting(meetingBody: MeetingBody) = fetch {
        service.scheduleMeeting(meetingBody)
    }

    fun getUserMeetings(userId: String) = fetch {
        service.getUserMeetings(userId)
    }
}