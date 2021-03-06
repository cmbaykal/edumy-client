package com.baykal.edumyclient.data.domain.meeting

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.meeting.request.MeetingBody
import com.baykal.edumyclient.data.repository.MeetingRepository
import javax.inject.Inject

class ScheduleMeetingUseCase @Inject constructor(
    private val meetingRepository: MeetingRepository
) : BaseUseCase<MeetingBody, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: MeetingBody) = meetingRepository.scheduleMeeting(params)
}