package com.baykal.edumyclient.ui.screen.account.register

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel(){

    private val uiState = MutableStateFlow(RegisterState())

    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    fun setName(state: InputState) {
        uiValue = uiValue.copy(name = state)
    }

    fun setSurname(state: InputState) {
        uiValue = uiValue.copy(surname = state)
    }

    fun setMail(state: InputState) {
        uiValue = uiValue.copy(mail = state)
    }

    fun setBirth(state: InputState) {
        uiValue = uiValue.copy(birth = state)
    }

    fun setPass(state: InputState) {
        uiValue = uiValue.copy(pass = state)
    }

    fun setPassConfirm(state: InputState) {
        uiValue = uiValue.copy(passConfirm = state)
    }

    // TODO: Terms and Conditions

    fun register() {
        controller.navigateUp()
    }

}