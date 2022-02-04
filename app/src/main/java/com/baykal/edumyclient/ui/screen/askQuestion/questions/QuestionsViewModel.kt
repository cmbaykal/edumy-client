package com.baykal.edumyclient.ui.screen.askQuestion.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baykal.edumyclient.data.model.account.user.LoginCredentials
import com.baykal.edumyclient.data.service.EdumyApi
import com.baykal.edumyclient.ui.component.InputState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val api: EdumyApi
) : ViewModel() {


}