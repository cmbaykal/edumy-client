package com.baykal.edumyclient.data.domain.study

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.study.request.StudyBody
import com.baykal.edumyclient.data.repository.StudyRepository
import javax.inject.Inject

class SendStudyUseCase @Inject constructor(
    private val studyRepository: StudyRepository
) : BaseUseCase<StudyBody, Unit>() {

    override fun build(params: StudyBody) = studyRepository.sendStudy(params)
}