package com.baykal.edumyclient.ui.screen.questionSection.questionDetail

import com.baykal.edumyclient.data.model.question.Question
import com.baykal.edumyclient.data.model.user.response.User

data class QuestionDetailState(
    val user: User,
    val question: Question,
)
