package com.baykal.edumyclient.ui.screen.questionSection.answers

import com.baykal.edumyclient.data.model.answer.Answer
import com.baykal.edumyclient.data.model.question.Question

data class AnswersState(
    val answers: MutableList<Answer>? = null,
    val userId: String? = null,
    val question: Question? = null,
    val imageDialogState: Boolean = false,
    val imageUri: String? = null,
)