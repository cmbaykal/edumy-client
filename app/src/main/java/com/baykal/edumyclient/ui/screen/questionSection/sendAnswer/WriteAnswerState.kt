package com.baykal.edumyclient.ui.screen.questionSection.sendAnswer

import android.net.Uri
import com.baykal.edumyclient.base.component.InputState

data class WriteAnswerState(
    val questionId: String? = null,
    val description: InputState = InputState(),
    val videoUri: Uri? = null,
    val imageUri: Uri? = null,
    val anonymousState: Boolean = false
) {
    val isFormValid get() = description.isSuccess
}