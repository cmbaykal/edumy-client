package com.baykal.edumyclient.ui.screen.account.login

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.account.LoginUseCase
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val session: EdumySession
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    private val uiState = _uiState.asStateFlow()


    fun setEmail(state: InputState) {
        _uiState.update { it.copy(email = state) }
    }

    fun setPass(state: InputState) {
        _uiState.update { it.copy(pass = state) }
    }

    fun login() {
        with(uiState.value) {
            if (email.isSuccess && pass.isSuccess) {
                loginUseCase.observe(
                    LoginCredentials(email.text, pass.text)
                ).collectData { response ->
                    response?.let {
                        session.saveUser(it)
                        controller.login()
                    }
                }
            }
        }
    }
}