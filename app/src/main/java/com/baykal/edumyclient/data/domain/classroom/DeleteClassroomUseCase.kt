package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.ClassroomRepository
import javax.inject.Inject

class DeleteClassroomUseCase @Inject constructor(
    private val classroomsRepository: ClassroomRepository
) : BaseUseCase<DeleteClassroomUseCase.Params, Unit>() {

    override fun build(params: Params) = classroomsRepository.deleteClass(params.classId, params.userMail)

    data class Params(val classId: String, val userMail: String?)

}