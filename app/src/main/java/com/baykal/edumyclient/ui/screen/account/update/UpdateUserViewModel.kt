package com.baykal.edumyclient.ui.screen.account.update

import at.favre.lib.crypto.bcrypt.BCrypt
import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.account.ChangePasswordUseCase
import com.baykal.edumyclient.data.domain.account.UserInformationUseCase
import com.baykal.edumyclient.data.domain.account.UpdateUserUseCase
import com.baykal.edumyclient.data.model.user.request.PasswordCredentials
import com.baykal.edumyclient.data.model.user.request.UpdateCredentials
import com.toxicbakery.bcrypt.Bcrypt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UpdateUserViewModel @Inject constructor(
    private val session: EdumySession,
    private val updateUserUseCase: UpdateUserUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val getUserUseCase: UserInformationUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(UpdateUserState())
    val uiState = _uiState.asStateFlow()

    fun setName(state: InputState) {
        _uiState.update { it.copy(name = state) }
    }

    fun setBio(state: InputState) {
        _uiState.update { it.copy(bio = state) }
    }

    fun setOldPass(state: InputState) {
        _uiState.update { it.copy(oldPass = state) }
    }

    fun setPass(state: InputState) {
        _uiState.update { it.copy(newPass = state) }
    }

    fun setPassConfirm(state: InputState) {
        _uiState.update { it.copy(newPassConfirm = state) }
    }

    fun checkPassword(text: String): Boolean = text == uiState.value.newPass.text

    fun getUserInformation() {
        session.withUser { user ->
            _uiState.update { it.copy(user = user) }
        }
    }

    fun updateUser() {
        session.withUserId { userId ->
            with(uiState.value) {
                updateUserUseCase.observe(
                    UpdateCredentials(
                        userId = userId,
                        name = name.text,
                        bio = bio.text
                    )
                ).collectData {
                    getUserUseCase.observe(userId).collectData { user ->
                        user?.let {
                            session.saveUser(it)
                        }
                        controller.showDialog(
                            title = "Update Success",
                            message = "Your profile successfully updated."
                        )
                    }
                }
            }
        }
    }

    fun changePassword() {
        session.withUserId {
            with(uiState.value) {
                if (passValid) {
                    changePasswordUseCase.observe(
                        PasswordCredentials(
                            userId = it,
                            oldPass = oldPass.text,
                            newPass = Bcrypt.hash(newPass.text, BCrypt.MIN_COST).decodeToString()
                        )
                    ).collectData {
                        controller.showDialog(
                            title = "Password Change Success",
                            message = "Your account password successfully updated."
                        )
                    }
                }
            }
        }
    }
}