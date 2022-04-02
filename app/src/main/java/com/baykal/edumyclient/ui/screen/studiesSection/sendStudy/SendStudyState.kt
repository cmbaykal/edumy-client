package com.baykal.edumyclient.ui.screen.studiesSection.sendStudy

import com.baykal.edumyclient.base.component.InputState

data class SendStudyState(
    val lesson: InputState = InputState(),
    val correctAnswers: InputState = InputState(),
    val wrongAnswers: InputState = InputState(),
    val emptyQuestions: InputState = InputState(),
) {
    val isFormValid get() = lesson.isSuccess && correctAnswers.isSuccess && wrongAnswers.isSuccess && emptyQuestions.isSuccess
}