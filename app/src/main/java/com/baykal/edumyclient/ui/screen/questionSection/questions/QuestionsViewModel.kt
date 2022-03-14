package com.baykal.edumyclient.ui.screen.questionSection.questions

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val session: EdumySession
) : BaseViewModel() {

    //    private val classroomList: MutableList<MenuItem.Questions> = mutableListOf()
    private val _uiState = MutableStateFlow(QuestionsState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        session.withUser { user ->
            _uiState.update { it.copy(userRole = user.role) }
        }
        getQuestions()
    }

    fun getQuestions() {

    }

    fun filterQuestions(lesson: String) {

    }

}