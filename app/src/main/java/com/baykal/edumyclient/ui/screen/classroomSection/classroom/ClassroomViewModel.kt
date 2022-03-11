package com.baykal.edumyclient.ui.screen.classroomSection.classroom

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.classroom.AssignClassroomUseCase
import com.baykal.edumyclient.data.domain.classroom.ClassroomInformationUseCase
import com.baykal.edumyclient.data.domain.classroom.DeleteClassroomUseCase
import com.baykal.edumyclient.data.domain.classroom.LeaveClassroomUseCase
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ClassroomViewModel @Inject constructor(
    private val session: EdumySession,
    private val classroomInformationUseCase: ClassroomInformationUseCase,
    private val assignClassroomUseCase: AssignClassroomUseCase,
    private val leaveClassroomUseCase: LeaveClassroomUseCase,
    private val deleteClassroomUseCase: DeleteClassroomUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ClassroomState())
    val uiState = _uiState.asStateFlow()

    fun getClassroomInformation() {
        args?.getString(ClassroomRoute.CLASS_ID)?.let { classId ->
            classroomInformationUseCase.observe(classId).collect { classroom ->
                _uiState.update { it.copy(classroom = classroom) }
                session.withUser { user ->
                    _uiState.update { it.copy(user = user) }
                    _uiState.update { it.copy(owner = user.id == classroom?.creatorId) }
                }
            }
        }
    }

    fun assignUser(classId: String?, userMail: String) {
        assignClassroomUseCase.observe(
            AssignClassroomUseCase.Params(classId, userMail)
        ).collect {
            controller.showDialog(
                "Assign Success",
                "You can now follow your student's progress in your classroom.",
            ) {
                getClassroomInformation()
            }
        }
    }

    fun leaveClassroom(classId: String) {
        session.withUser {
            leaveClassroomUseCase.observe(
                LeaveClassroomUseCase.Params(classId, it.mail)
            ).collect {
                controller.navigateToRoute(ClassroomsRoute.route, true)
            }
        }
    }

    fun deleteClassroom(classId: String) {
        session.withUser {
            deleteClassroomUseCase.observe(
                DeleteClassroomUseCase.Params(classId, it.mail)
            ).collect {
                controller.navigateToRoute(ClassroomsRoute.route, true)
            }
        }
    }
}
