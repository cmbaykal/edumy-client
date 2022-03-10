package com.baykal.edumyclient.ui.screen.classroomSection.createClass

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.CreateClassroomUseCase
import com.baykal.edumyclient.data.model.classroom.request.ClassroomBody
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val session: EdumySession,
    private val createClassroomUseCase: CreateClassroomUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(CreateClassState())
    private val uiState = _uiState.asStateFlow()

    fun setClassName(state: InputState) {
        _uiState.update { it.copy(name = state) }
    }

    fun setClassLesson(state: InputState) {
        _uiState.update { it.copy(lesson = state) }
    }

    fun createClass() {
        with(uiState.value){
            if (isFormValid) {
                session.withUserId {
                    createClassroomUseCase.observe(
                        ClassroomBody(
                            lesson.text,
                            name.text,
                            it
                        )
                    ).collect {
                        navigate(ClassroomsRoute.route, true)
                    }
                }
            }
        }
    }
}
