package com.baykal.edumyclient.ui.screen.classroom.createClass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baykal.edumyclient.data.model.account.user.LoginCredentials
import com.baykal.edumyclient.data.service.EdumyApi
import com.baykal.edumyclient.ui.component.InputState
import com.baykal.edumyclient.ui.screen.account.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val api: EdumyApi
) : ViewModel() {


}