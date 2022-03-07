package com.baykal.edumyclient.data.domain.classroom

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import com.baykal.edumyclient.data.repository.ClassroomsRepository
import javax.inject.Inject

class UserClassroomsUseCase @Inject constructor(
    private val classroomsRepository: ClassroomsRepository
) : BaseUseCase<String, BaseResult<ApiResponse<MutableList<Classroom>>>>() {

    override fun build(params: String) = classroomsRepository.getUserClassrooms(params)

}