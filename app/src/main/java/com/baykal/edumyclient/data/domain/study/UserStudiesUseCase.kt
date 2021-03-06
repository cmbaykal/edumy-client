package com.baykal.edumyclient.data.domain.study

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseResult
import com.baykal.edumyclient.base.data.BaseUseCase
import com.baykal.edumyclient.data.model.study.response.Study
import com.baykal.edumyclient.data.repository.StudyRepository
import javax.inject.Inject

class UserStudiesUseCase @Inject constructor(
    private val studyRepository: StudyRepository
) : BaseUseCase<String, BaseResult<ApiResponse<MutableList<Study>>>>() {

    override fun build(params: String) = studyRepository.getUserStudies(params)
}