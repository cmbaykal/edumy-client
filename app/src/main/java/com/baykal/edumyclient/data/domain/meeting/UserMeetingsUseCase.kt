package com.baykal.edumyclient.data.domain.meeting

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.meeting.response.Meeting
import com.baykal.edumyclient.data.repository.MeetingRepositoryImpl
import javax.inject.Inject

class UserMeetingsUseCase @Inject constructor(
    private val meetingRepository: MeetingRepositoryImpl
) : BaseUseCase<String, MutableList<Meeting>>() {

    override fun build(params: String) = meetingRepository.getUserMeetings(params)
}