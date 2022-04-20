package com.baykal.edumyclient.ui.screen.questionSection.sendAnswer

import android.app.Application
import android.net.Uri
import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.extension.toJson
import com.baykal.edumyclient.base.network.ContentUriRequestBody
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.answer.SendAnswerUseCase
import com.baykal.edumyclient.ui.screen.questionSection.questionDetail.QuestionDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.MultipartBody
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
                    val body = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)

                    body.addFormDataPart("questionId", questionId.toString())
                        .addFormDataPart("description", description.text)
                        .addFormDataPart("date", date)

                    if (!anonymousState) {
                        body.addFormDataPart("userId", it)
                    }

                    imageUri?.let { uri ->
                        val fileBody = ContentUriRequestBody(application.contentResolver, uri)
                        body.addFormDataPart(
                            "image",
                            "questionImage.jpg",
                            fileBody
                        )
                    }

                    videoUri?.let { uri ->
                        val fileBody = ContentUriRequestBody(application.contentResolver, uri)
                        body.addFormDataPart(
                            "video",
                            "questionVideo.mp4",
                            fileBody
                        )
                    }

                    sendAnswerUseCase.observe(
                        body.build()
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