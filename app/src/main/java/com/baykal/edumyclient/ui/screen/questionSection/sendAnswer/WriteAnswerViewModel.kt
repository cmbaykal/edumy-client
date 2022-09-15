package com.baykal.edumyclient.ui.screen.questionSection.sendAnswer

import android.app.Application
import android.net.Uri
import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.extension.toJson
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.answer.SendAnswerUseCase
import com.baykal.edumyclient.ui.screen.questionSection.questionDetail.QuestionDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WriteAnswerViewModel @Inject constructor(
    private val application: Application,
    private val session: EdumySession,
    private val sendAnswerUseCase: SendAnswerUseCase
) : BaseViewModel() {

    private var questionId: String? = null

    private val _uiState = MutableStateFlow(WriteAnswerState())
    val uiState = _uiState.asStateFlow()


    fun setDescription(state: InputState) {
        _uiState.update { it.copy(description = state) }
    }

    fun setAnonymous(state: Boolean) {
        _uiState.update { it.copy(anonymousState = state) }
    }

    fun setImage(uri: Uri) {
        _uiState.update { it.copy(imageUri = uri) }
    }

    fun setVideo(uri: Uri) {
        _uiState.update { it.copy(videoUri = uri) }
    }

    fun fetchData() {
        args?.getString(QuestionDetailRoute.QUESTION_ID)?.let { id ->
            _uiState.update { it.copy(questionId = id) }
        }
    }

    fun sendAnswer() {
        with(uiState.value) {
            if (isFormValid) {
                session.withUserId {
                    val date = Date().toJson
                    val parts = formData {
                        if (!anonymousState) {
                            append("userId", it)
                        }

                        append("questionId", questionId.toString())
                        append("description", description.text)
                        append("date", date)

                        imageUri?.let { uri ->
                            val inputStream = application.contentResolver.openInputStream(uri)
                            val imageByteArray = inputStream?.readBytes()
                            imageByteArray?.let {
                                append("image", imageByteArray, Headers.build {
                                    append(HttpHeaders.ContentType, "image/jpeg")
                                    append(HttpHeaders.ContentDisposition, "filename=answerImage.png")
                                })
                            }
                        }

                        videoUri?.let { uri ->
                            val inputStream = application.contentResolver.openInputStream(uri)
                            val viewByteArray = inputStream?.readBytes()
                            viewByteArray?.let {
                                append("video", viewByteArray, Headers.build {
                                    append(HttpHeaders.ContentType, "image/jpeg")
                                    append(HttpHeaders.ContentDisposition, "filename=answerVideo.png")
                                })
                            }
                        }
                    }

                    sendAnswerUseCase.observe(
                        parts
                    ).collectData {
                        controller.showDialog(
                            "Answer Sent",
                            "Your answer is successfully send.",
                        ) {
                            controller.navigateUp()
                        }
                    }
                }
            }
        }
    }
}