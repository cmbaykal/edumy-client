package com.baykal.edumyclient.ui.screen.account.register

import at.favre.lib.crypto.bcrypt.BCrypt
import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.account.RegisterUseCase
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.toxicbakery.bcrypt.Bcrypt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState = _uiState.asStateFlow()

    fun setName(state: InputState) {
        _uiState.update { it.copy(name = state) }
    }

    fun setSurname(state: InputState) {
        _uiState.update { it.copy(surname = state) }
    }

    fun setMail(state: InputState) {
        _uiState.update { it.copy(mail = state) }
    }

    fun setBirth(state: InputState) {
        state.isSuccess = true
        _uiState.update { it.copy(birth = state) }
    }

    fun setPass(state: InputState) {
        _uiState.update { it.copy(pass = state) }
    }

    fun setPassConfirm(state: InputState) {
        _uiState.update { it.copy(passConfirm = state) }
    }

    fun setRole(checked: Boolean) {
        val role = if (checked) UserRole.Teacher else UserRole.Student
        _uiState.update { it.copy(role = role) }
    }

    fun checkPassword(text: String): Boolean = text == uiState.value.pass.text

    // TODO: Terms and Conditions

    fun register() {
        with(uiState.value) {
            if (isFormValid) {
                registerUseCase.observe(
                    RegisterCredentials(
                        mail = mail.text,
                        pass = Bcrypt.hash(pass.text, BCrypt.MIN_COST).decodeToString(),
                        birth = birthDate,
                        name = "${name.text} ${surname.text}",
                        role = role
                    )
                ).collect {
                    controller.showDialog(
                        "Register Success",
                        "Your account successfully created. You can now login.",
                    ) {
                        controller.navigateUp()
                    }
                }
            }
        }
    }
}