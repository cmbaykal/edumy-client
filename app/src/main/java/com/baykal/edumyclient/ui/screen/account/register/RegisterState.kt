package com.baykal.edumyclient.ui.screen.account.register

data class RegisterState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val success: Boolean = false
)