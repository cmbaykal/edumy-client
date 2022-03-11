package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.ClassroomsRepository
import javax.inject.Inject

class DeleteClassroomUseCase @Inject constructor(
    private val classroomsRepository: ClassroomsRepository
) : BaseUseCase<DeleteClassroomUseCase.Params, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: Params) = classroomsRepository.deleteClass(params.classId, params.userMail)

    data class Params(val classId: String, val userMail: String?)

}