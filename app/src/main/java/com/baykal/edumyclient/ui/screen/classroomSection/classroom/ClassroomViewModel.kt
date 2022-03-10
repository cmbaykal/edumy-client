package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.ClassroomInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ClassroomViewModel @Inject constructor(
    private val session: EdumySession,
    private val classroomInformationUseCase: ClassroomInformationUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ClassroomState())
    val uiState = _uiState.asStateFlow()

    fun getClassroomInformation() {
        args?.let { bundle ->
            bundle.getString(ClassroomRoute.CLASS_ID)?.let { classId ->
                classroomInformationUseCase.observe(classId).collect { classroom ->
                    _uiState.update { it.copy(classroom = classroom) }
                    session.withUser { user ->
                        _uiState.update { it.copy(user = user) }
                        _uiState.update { it.copy(owner = user.id == classroom?.creatorId) }
                    }
                }
            }
        }
    }
}
