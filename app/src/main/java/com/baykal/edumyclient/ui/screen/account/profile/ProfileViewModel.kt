package com.baykal.edumyclient.ui.screen.account.profile

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.account.UserInformationUseCase
import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val session: EdumySession,
    private val getUserUseCase: UserInformationUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState = _uiState.asStateFlow()

    fun getUserInformation() {
        session.withUser { user ->
            args?.getString(ProfileRoute.USER_ID)?.let { userId ->
                if (userId == user.id) {
                    null
                } else {
                    getUserUseCase.observe(userId).collectData { user ->
                        _uiState.update { it.copy(user = user, currentUser = false) }
                    }
                }
            } ?: run {
                _uiState.update { it.copy(user = user, currentUser = true) }
            }
        }
    }

    fun logout() {
        session.drop()
        controller.navigateToRoute(LoginRoute.route, true)
    }
}