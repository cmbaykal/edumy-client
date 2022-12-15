package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.ClassroomRepositoryImpl
import javax.inject.Inject

class AssignClassroomUseCase @Inject constructor(
    private val classroomsRepository: ClassroomRepositoryImpl
) : BaseUseCase<AssignClassroomUseCase.Params, Unit>() {

    override fun build(params: Params) = classroomsRepository.assignUserToClassroom(params.classId, params.userId)

    data class Params(val classId: String?, val userId: String)
}