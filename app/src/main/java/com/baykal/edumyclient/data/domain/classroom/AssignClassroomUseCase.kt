package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.ClassroomsRepository
import javax.inject.Inject

class AssignClassroomUseCase @Inject constructor(
    private val classroomsRepository: ClassroomsRepository
) : BaseUseCase<AssignClassroomUseCase.Params, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: Params) = classroomsRepository.assignClass(params.classId, params.userId)

    data class Params(val classId: String, val userId: String)

}