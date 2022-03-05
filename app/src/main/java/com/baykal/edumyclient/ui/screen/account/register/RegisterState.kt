package com.baykal.edumyclient.ui.screen.account.register

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.ui.UIState

data class RegisterState(
    val name: InputState = InputState(),
    val surname: InputState = InputState(),
    val mail: InputState = InputState(),
    val birth: InputState = InputState(),
    val pass: InputState = InputState(),
    val passConfirm: InputState = InputState(),
) : UIState() {
    val isFormValid get() = name.isSuccess && surname.isSuccess && mail.isSuccess && birth.isSuccess && pass.isSuccess && passConfirm.isSuccess
    val birthDate get() = "${birth.text} 00.00.00"
}