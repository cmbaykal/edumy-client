package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.repository.ClassroomRepository
import javax.inject.Inject

class ClassroomInformationUseCase @Inject constructor(
    private val classroomsRepository: ClassroomRepository
) : BaseUseCase<String, Classroom>() {

    override fun build(params: String) = classroomsRepository.getClassInformation(params)

}