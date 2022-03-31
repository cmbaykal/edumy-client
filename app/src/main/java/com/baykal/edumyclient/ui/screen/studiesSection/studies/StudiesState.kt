package com.baykal.edumyclient.ui.screen.studiesSection.studies

import com.baykal.edumyclient.data.model.study.response.Study
import com.baykal.edumyclient.data.model.user.response.UserRole

data class StudiesState(
    val userRole: UserRole? = null,
    val lesson: String = "All",
    val studies: MutableList<Study>? = null
)