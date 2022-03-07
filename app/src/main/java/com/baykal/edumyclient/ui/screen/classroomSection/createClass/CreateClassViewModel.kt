package com.baykal.edumyclient.ui.screen.classroomSection.createClass

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.CreateClassroomUseCase
import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val session: EdumySession,
    private val createClassroomUseCase: CreateClassroomUseCase
) : BaseViewModel() {

    private val uiState = MutableStateFlow(CreateClassState())

    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    fun setClassName(state: InputState) {
        uiValue = uiValue.copy(name = state)
    }

    fun setClassLesson(state: InputState) {
        uiValue = uiValue.copy(lesson = state)
    }

    fun createClass() {
        if (uiValue.isFormValid) {
            session.userId?.let {
                createClassroomUseCase.observe(
                    ClassroomBody(
                        uiValue.lesson.text,
                        uiValue.name.text,
                        it
                    )
                ).collect {
                    controller.navigateToRoute(ClassroomsRoute.route, true)
                }
            }
        }
    }
}
