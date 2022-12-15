package com.baykal.edumyclient.data.domain.meeting

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.meeting.request.MeetingBody
import com.baykal.edumyclient.data.repository.MeetingRepositoryImpl
import javax.inject.Inject

class ScheduleMeetingUseCase @Inject constructor(
    private val meetingRepository: MeetingRepositoryImpl
) : BaseUseCase<MeetingBody, Unit>() {

    override fun build(params: MeetingBody) = meetingRepository.scheduleMeeting(params)
}