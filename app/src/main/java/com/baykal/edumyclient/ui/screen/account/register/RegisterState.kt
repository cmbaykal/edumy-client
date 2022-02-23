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
) : UIState()