package com.baykal.edumyclient.ui.screen.account.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.component.InputState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val uiState = MutableStateFlow(LoginState())

    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    fun setEmail(state: InputState) {
        uiValue = uiValue.copy(email = state)
    }

    fun setPass(state: InputState) {
        uiValue = uiValue.copy(pass = state)
    }

    fun login() {
        if (!uiValue.email.isError && !uiValue.pass.isError) {
            Log.d("EdumyTest", "It Worked")
        }
    }

}