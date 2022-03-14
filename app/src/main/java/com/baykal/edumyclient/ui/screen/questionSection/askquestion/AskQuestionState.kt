package com.baykal.edumyclient.ui.screen.questionSection.askquestion

import android.net.Uri
import com.baykal.edumyclient.base.component.InputState

data class AskQuestionState(
    val lesson: InputState = InputState(),
    val question: InputState = InputState(),
    val imageUri: Uri? = null,
    val anonymousState: Boolean = false,
) {
    val isFormValid get() = lesson.isSuccess && question.isSuccess
}
