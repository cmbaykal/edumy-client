package com.baykal.edumyclient.ui.screen.questionSection.questionDetail

import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.model.question.Question

data class QuestionDetailState(
    var userId: String? = null,
    val question: Question? = null,
    val answers: MutableList<Answer> = mutableListOf(),
    val imageDialogState: Boolean = false,
    val imageUri: String? = null,
    val videoDialogState: Boolean = false,
    val videoUri: String? = null
)
