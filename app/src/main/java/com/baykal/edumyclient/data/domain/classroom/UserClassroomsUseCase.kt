package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.repository.ClassroomRepository
import javax.inject.Inject

class UserClassroomsUseCase @Inject constructor(
    private val classroomsRepository: ClassroomRepository
) : BaseUseCase<String, MutableList<Classroom>>() {

    override fun build(params: String) = classroomsRepository.getUserClassrooms(params)

}