package com.baykal.edumyclient.ui.screen.studiesSection.sendStudy

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.extension.toJson
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.study.SendStudyUseCase
import com.baykal.edumyclient.data.model.study.request.StudyBody
import com.baykal.edumyclient.ui.screen.studiesSection.studies.StudiesRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SendStudyViewModel @Inject constructor(
    private val session: EdumySession,
    private val sendStudyUseCase: SendStudyUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(SendStudyState())
    val uiState = _uiState.asStateFlow()

    fun setLesson(state: InputState) {
        _uiState.update { it.copy(lesson = state) }
    }

    fun setCorrectAnswers(state: InputState) {
        _uiState.update { it.copy(correctAnswers = state) }
    }

    fun setWrongAnswers(state: InputState) {
        _uiState.update { it.copy(wrongAnswers = state) }
    }

    fun setEmptyAnswers(state: InputState) {
        _uiState.update { it.copy(emptyAnswers = state) }
    }

    fun sendStudy() {
        with(uiState.value) {
            if (isFormValid) {
                session.withUserId { id ->
                    val date = Date().toJson
                    sendStudyUseCase.observe(
                        StudyBody(
                            userId = id,
                            lesson = lesson.text,
                            correctA = correctAnswers.text,
                            wrongA = wrongAnswers.text,
                            emptyA = emptyAnswers.text,
                            date = date
                        )
                    ).collectData {
                        controller.showDialog(
                            "Study Sent",
                            "Your study is successfully send.",
                        ) {
                            navigate(StudiesRoute.route, true)
                        }
                    }
                }
            }
        }
    }
}