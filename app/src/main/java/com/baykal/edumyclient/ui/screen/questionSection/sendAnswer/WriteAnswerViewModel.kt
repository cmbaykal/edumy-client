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

                        // TODO : Image File Part
//                        imageUri?.let { uri ->
//                            val fileBody = ContentUriRequestBody(application.contentResolver, uri)
//                            body.addFormDataPart(
//                                "image",
//                                "questionImage.jpg",
//                                fileBody
//                            )
//                        }
//
                        // TODO : Video File Part
//                        videoUri?.let { uri ->
//                            val fileBody = ContentUriRequestBody(application.contentResolver, uri)
//                            body.addFormDataPart(
//                                "video",
//                                "questionVideo.mp4",
//                                fileBody
//                            )
//                        }
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