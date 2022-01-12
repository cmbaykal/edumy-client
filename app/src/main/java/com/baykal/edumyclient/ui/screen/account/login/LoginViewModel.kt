package com.baykal.edumyclient.ui.screen.account.login

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
class LoginViewModel @Inject constructor(
    private val api: EdumyApi
) : ViewModel() {

    val uiState = MutableStateFlow(LoginState())
    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    fun setEmail(state: InputState) {
        uiValue = uiValue.copy(email = state)
    }

    fun setPass(state:InputState) {
        uiValue = uiValue.copy(pass = state)
    }

    fun login(credentials: LoginCredentials) {
        viewModelScope.launch {
            val response = api.LoginUser(credentials)
        }
    }
}