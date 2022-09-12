package com.baykal.edumyclient.ui.screen.questionSection.askquestion

import android.app.Application
import android.net.Uri
import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.extension.toJson
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.question.AskQuestionUseCase
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.request.forms.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AskQuestionViewModel @Inject constructor(
    private val application: Application,
    private val session: EdumySession,
    private val askQuestionUseCase: AskQuestionUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(AskQuestionState())
    val uiState = _uiState.asStateFlow()

    fun setLesson(state: InputState) {
        _uiState.update { it.copy(lesson = state) }
    }

    fun setDescription(state: InputState) {
        _uiState.update { it.copy(description = state) }
    }

    fun setAnonymous(state: Boolean) {
        _uiState.update { it.copy(anonymousState = state) }
    }

    fun setImage(uri: Uri) {
        _uiState.update { it.copy(imageUri = uri) }
    }

    fun sendQuestion() {
        with(uiState.value) {
            if (isFormValid) {
                session.withUserId {
                    val date = Date().toJson
                    val parts = formData {
                        if (!anonymousState) {
                            append("userId", it)
                            append("lesson", lesson.text)
                            append("description", description.text)
                            append("date", date)
                        }
                        // TODO: Image File Part
//                        imageUri?.let { uri ->
//                            val fileBody = ContentUriRequestBody(application.contentResolver, uri)
//                            append("image", File("questionImage.jpg").readBytes(), Headers.build {
//                                append(HttpHeaders.ContentType, "image/jpg")
//                                append(HttpHeaders.ContentDisposition, "filename=\"questionImage.png\"")
//                            })
//                        }
                    }

                    askQuestionUseCase.observe(
                        parts
                    ).collectData {
                        controller.showDialog(
                            "Question Sent",
                            "Your question is successfully send.",
                        ) {
                            navigate(QuestionsRoute.feed(), true)
                        }
                    }
                }
            }
        }
    }
}