package com.baykal.edumyclient.ui.screen.classroomSection.createClass

import com.baykal.edumyclient.base.component.InputState

data class CreateClassState(
    val name: InputState = InputState(),
    val lesson: InputState = InputState(),
) {
    val isFormValid get() = name.isSuccess && lesson.isSuccess
}
