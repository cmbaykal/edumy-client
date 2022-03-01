package com.baykal.edumyclient.ui.screen.account.login

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.LoginUseCase
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

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
            loginUseCase.observe(
                LoginCredentials(uiValue.email.text, uiValue.pass.text)
            ).collect { response ->
                response?.let {
                    if (it.success) {
                        controller.navigateToRoute(ClassroomsRoute.route, true)
                    }
                }
            }
        }
    }
}