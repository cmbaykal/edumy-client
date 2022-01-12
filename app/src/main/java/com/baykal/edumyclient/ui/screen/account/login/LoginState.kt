package com.baykal.edumyclient.ui.screen.account.login

import com.baykal.edumyclient.ui.component.InputState

data class LoginState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val success: Boolean = false,
    val email: InputState = InputState(),
    val pass: InputState = InputState()
)