package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.ClassroomRepositoryImpl
import javax.inject.Inject

class DeleteClassroomUseCase @Inject constructor(
    private val classroomsRepository: ClassroomRepositoryImpl
) : BaseUseCase<DeleteClassroomUseCase.Params, Unit>() {

    override fun build(params: Params) = classroomsRepository.deleteClassroom(params.classId, params.userMail)

    data class Params(val classId: String, val userMail: String?)
}