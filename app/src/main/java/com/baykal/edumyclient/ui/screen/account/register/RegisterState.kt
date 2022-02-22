package com.baykal.edumyclient.ui.screen.account.register

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.ui.UIState

data class RegisterState(
    val email: InputState = InputState(),
    val pass: InputState = InputState()
) : UIState()