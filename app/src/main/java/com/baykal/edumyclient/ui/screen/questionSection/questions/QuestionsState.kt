package com.baykal.edumyclient.ui.screen.questionSection.questions

import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.user.response.UserRole

data class QuestionsState(
    val isMoreData:Boolean = true,
    val lesson: String = "All",
    val userRole: UserRole? = null,
    val questions: MutableList<Question>? = null,
)