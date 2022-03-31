package com.baykal.edumyclient.data.domain.study

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.study.request.StudyBody
import com.baykal.edumyclient.data.repository.StudyRepository
import javax.inject.Inject

class SendStudyUseCase @Inject constructor(
    private val studyRepository: StudyRepository
) : BaseUseCase<StudyBody, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: StudyBody) = studyRepository.sendStudy(params)
}