package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.meeting.request.MeetingBody
import com.baykal.edumyclient.data.model.meeting.response.Meeting
import kotlinx.coroutines.flow.Flow

abstract class MeetingRepository : BaseRepository() {
    abstract fun scheduleMeeting(meetingBody: MeetingBody): Flow<ApiResponse<out Unit>>
    abstract fun getUserMeetings(userId: String): Flow<ApiResponse<out MutableList<Meeting>>>
}