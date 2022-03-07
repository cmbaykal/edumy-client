package com.baykal.edumyclient.ui.screen.account.register

import at.favre.lib.crypto.bcrypt.BCrypt
import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.account.RegisterUseCase
import com.baykal.edumyclient.data.model.user.UserRole
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.toxicbakery.bcrypt.Bcrypt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

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
        state.isSuccess = true
        uiValue = uiValue.copy(birth = state)
    }

    fun setPass(state: InputState) {
        uiValue = uiValue.copy(pass = state)
    }

    fun setPassConfirm(state: InputState) {
        uiValue = uiValue.copy(passConfirm = state)
    }

    fun checkPassword(text: String): Boolean = text == uiValue.pass.text

    // TODO: Terms and Conditions

    fun register() {
        with(uiValue) {
            if (isFormValid) {
                registerUseCase.observe(
                    RegisterCredentials(
                        mail = mail.text,
                        pass = Bcrypt.hash(pass.text, BCrypt.MIN_COST).decodeToString(),
                        birth = birthDate,
                        name = "${name.text} ${surname.text}",
                        role = UserRole.Student
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