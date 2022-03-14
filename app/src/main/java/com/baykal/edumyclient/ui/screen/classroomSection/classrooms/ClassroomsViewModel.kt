package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.UserClassroomsUseCase
import com.baykal.edumyclient.data.model.classroom.response.Classroom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ClassroomsViewModel @Inject constructor(
    private val session: EdumySession,
    private val userClassroomsUseCase: UserClassroomsUseCase
) : BaseViewModel() {

    private val classroomList: MutableList<Classroom> = mutableListOf()
    private val _uiState = MutableStateFlow(ClassroomsState())
    val uiState = _uiState.asStateFlow()

    init {
        getClassrooms()
    }

    fun getClassrooms() {
        session.withUser { user ->
            _uiState.update { it.copy(userRole = user.role) }
        }
        session.withUserId { id ->
            userClassroomsUseCase.observe(id)
                .collectData { classList ->
                    classList?.let { list ->
                        classroomList.clear()
                        classroomList.addAll(list)
                        _uiState.update { it.copy(classrooms = classroomList) }
                    }
                }
        }
    }

    private fun setSearchText(text: String) {
        _uiState.update { it.copy(searchText = text) }
        if (text.isEmpty()) {
            _uiState.update { it.copy(classrooms = classroomList) }
        }
    }

    fun filterClasses(text: String = uiState.value.searchText) {
        setSearchText(text)
        val list = if (uiState.value.searchText.isEmpty()) {
            classroomList
        } else {
            classroomList.filter {
                it.name?.contains(uiState.value.searchText, true) ?: false || it.lesson?.contains(uiState.value.searchText, true) ?: false
            }.toMutableList()
        }
        _uiState.update { it.copy(classrooms = list) }
    }
}