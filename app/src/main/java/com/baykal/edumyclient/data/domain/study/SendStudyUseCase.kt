package com.baykal.edumyclient.data.domain.study

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.study.request.StudyBody
import com.baykal.edumyclient.data.repository.StudyRepositoryImpl
import javax.inject.Inject

class SendStudyUseCase @Inject constructor(
    private val studyRepository: StudyRepositoryImpl
) : BaseUseCase<StudyBody, Unit>() {

    override fun build(params: StudyBody) = studyRepository.sendStudy(params)
}