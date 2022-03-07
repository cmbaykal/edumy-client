package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import androidx.compose.runtime.mutableStateOf
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.UserClassroomsUseCase
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassroomsViewModel @Inject constructor(
    private val session: EdumySession,
    private val userClassroomsUseCase: UserClassroomsUseCase
) : BaseViewModel() {

    private val classroomList: MutableList<Classroom> = mutableListOf()
    private val uiState = mutableStateOf(ClassroomsState())

    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    init {
        getClassrooms()
    }

    private fun getClassrooms() {
        session.userId?.let { id ->
            userClassroomsUseCase.observe(id)
                .collect { classList ->
                    classList?.let {
                        classroomList.clear()
                        classroomList.addAll(it)
                        uiValue = uiValue.copy(classrooms = classroomList)
                    }
                }
        }
    }

    private fun setSearchText(text: String) {
        uiValue = uiValue.copy(searchText = text)
        if (text.isEmpty()) {
            uiValue = uiValue.copy(classrooms = classroomList)
        }
    }

    fun filterClasses(text: String = uiValue.searchText) {
        setSearchText(text)
        val list = if (uiValue.searchText.isEmpty()) {
            classroomList
        } else {
            classroomList.filter {
                it.name?.contains(uiValue.searchText, true) ?: false || it.lesson?.contains(uiValue.searchText, true) ?: false
            }.toMutableList()
        }
        uiValue = uiValue.copy(classrooms = list)
    }
}