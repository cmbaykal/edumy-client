package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.data.model.meeting.request.MeetingBody
import com.baykal.edumyclient.data.service.EdumyServiceImp
import javax.inject.Inject

class MeetingRepositoryImpl @Inject constructor(
    private val service: EdumyServiceImp
) : MeetingRepository() {

    override fun scheduleMeeting(meetingBody: MeetingBody) = fetch {
        service.scheduleMeeting(meetingBody)
    }

    override fun getUserMeetings(userId: String) = fetch {
        service.getUserMeetings(userId)
    }
}