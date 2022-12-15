package com.baykal.edumyclient.data.domain.study

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.study.response.Study
import com.baykal.edumyclient.data.repository.StudyRepositoryImpl
import javax.inject.Inject

class ClassroomStudiesUseCase @Inject constructor(
    private val studyRepository: StudyRepositoryImpl
) : BaseUseCase<String, MutableList<Study>>() {

    override fun build(params: String) = studyRepository.getClassroomStudies(params)
}