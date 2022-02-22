package com.baykal.edumyclient.ui.screen.account.login

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.ui.UIState

data class LoginState(
    val email: InputState = InputState(),
    val pass: InputState = InputState()
) : UIState()