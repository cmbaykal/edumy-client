package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.ClassroomInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ClassroomViewModel @Inject constructor(
    private val classroomInformationUseCase: ClassroomInformationUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ClassroomState())
    val uiState = _uiState.asStateFlow()

    fun getClassroomInformation() {
        args?.let { bundle ->
            bundle.getString(ClassroomRoute.CLASS_ID)?.let {
                classroomInformationUseCase.observe(it).collect { classroom ->
                    classroom?.let {
                        _uiState.value = _uiState.value.copy(classroom = classroom)
                    }
                }
            }
        }
    }
}
