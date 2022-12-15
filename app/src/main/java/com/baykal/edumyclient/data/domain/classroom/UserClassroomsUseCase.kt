package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.repository.ClassroomRepositoryImpl
import javax.inject.Inject

class UserClassroomsUseCase @Inject constructor(
    private val classroomsRepository: ClassroomRepositoryImpl
) : BaseUseCase<String, MutableList<Classroom>>() {

    override fun build(params: String) = classroomsRepository.getUserClassrooms(params)

}