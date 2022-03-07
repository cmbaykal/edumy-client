package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.repository.ClassroomsRepository
import javax.inject.Inject

class AssignClassroomUseCase @Inject constructor(
    private val classroomsRepository: ClassroomsRepository
) : BaseUseCase<Pair<String, String>, BaseResult<ApiResponse<Unit>>>() {

    override fun build(params: Pair<String, String>) = classroomsRepository.assignClass(params.first, params.second)

}