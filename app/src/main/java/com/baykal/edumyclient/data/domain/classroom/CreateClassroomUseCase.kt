package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.data.repository.ClassroomRepositoryImpl
import javax.inject.Inject

class CreateClassroomUseCase @Inject constructor(
    private val classroomsRepository: ClassroomRepositoryImpl
) : BaseUseCase<ClassroomBody, Unit>() {

    override fun build(params: ClassroomBody) = classroomsRepository.addClassroom(params)
}