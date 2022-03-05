package com.baykal.edumyclient.ui.screen.account.login

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.LoginUseCase
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val session: EdumySession
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
        if (uiValue.email.isSuccess && uiValue.pass.isSuccess) {
            loginUseCase.observe(
                LoginCredentials(uiValue.email.text, uiValue.pass.text)
            ).collect { response ->
                response?.id?.let {
                    session.saveUserId(it)
                    controller.navigateToRoute(ClassroomsRoute.route, true)
                }
            }
        }
    }
}