package com.baykal.edumyclient.ui.screen.account.update

import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.data.model.user.response.User

data class UpdateUserState(
    val user: User? = null,
    val name: InputState = InputState(),
    val bio: InputState = InputState(),
    val oldPass: InputState = InputState(),
    val newPass: InputState = InputState(),
    val newPassConfirm: InputState = InputState(),
) {
    val isFormValid get() = name.isSuccess && bio.isSuccess
    val passValid get() = oldPass.isSuccess && newPass.isSuccess && newPassConfirm.isSuccess
}