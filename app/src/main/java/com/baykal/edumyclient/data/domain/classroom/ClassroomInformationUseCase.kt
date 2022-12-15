package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.repository.ClassroomRepositoryImpl
import javax.inject.Inject

class ClassroomInformationUseCase @Inject constructor(
    private val classroomsRepository: ClassroomRepositoryImpl
) : BaseUseCase<String, Classroom>() {

    override fun build(params: String) = classroomsRepository.getClassroomInformation(params)
}