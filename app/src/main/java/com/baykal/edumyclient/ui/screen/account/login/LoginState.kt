package com.baykal.edumyclient.ui.screen.account.login

import com.baykal.edumyclient.base.component.InputState

data class LoginState(
    val email: InputState = InputState(),
    val pass: InputState = InputState()
)