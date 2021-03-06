package com.baykal.edumyclient.ui.screen.studiesSection.studies

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.study.ClassroomStudiesUseCase
import com.baykal.edumyclient.data.domain.study.StudiesUseCase
import com.baykal.edumyclient.data.domain.study.UserStudiesUseCase
import com.baykal.edumyclient.data.model.study.response.Study
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StudiesViewModel @Inject constructor(
    private val session: EdumySession,
    private val userStudiesUseCase: UserStudiesUseCase,
    private val classroomStudiesUseCase: ClassroomStudiesUseCase,
    private val studiesUseCase: StudiesUseCase
) : BaseViewModel() {

    private var userId: String? = null
    private var classId: String? = null

    private val studyList: MutableList<Study> = mutableListOf()
    private val _uiState = MutableStateFlow(StudiesState())
    val uiState = _uiState.asStateFlow()

    fun fetchData() {
        session.withUser { user ->
            args?.getString(StudiesRoute.USER_ID)?.let { id ->
                userId = id
            } ?: args?.getString(QuestionsRoute.CLASS_ID)?.let {
                classId = it
            } ?: run {
                _uiState.update { it.copy(userRole = user.role) }
            }
            fetchStudies()
        }
    }

    fun fetchStudies() {
        userId?.let { id ->
            userStudiesUseCase.observe(id).collectData { response ->
                response?.let { list ->
                    studyList.clear()
                    studyList.addAll(list)
                    _uiState.update { it.copy(studies = studyList) }
                }
            }
        } ?: classId?.let { id ->
            classroomStudiesUseCase.observe(id).collectData { response ->
                response?.let { list ->
                    studyList.clear()
                    studyList.addAll(list)
                    _uiState.update { it.copy(studies = studyList) }
                }
            }
        } ?: run {
            session.withUserId { id ->
                studiesUseCase.observe(id).collectData { response ->
                    response?.let { list ->
                        studyList.clear()
                        studyList.addAll(list)
                        _uiState.update { it.copy(studies = studyList) }
                    }
                }
            }
        }
    }

    fun filterStudies(lesson: String) {
        _uiState.update { it.copy(lesson = lesson) }
        val list = if (lesson == "All") {
            studyList
        } else {
            studyList.filter {
                it.lesson == lesson
            }.toMutableList()
        }
        _uiState.update { it.copy(studies = list) }
    }
}