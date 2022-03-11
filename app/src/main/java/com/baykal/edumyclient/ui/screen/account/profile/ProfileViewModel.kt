package com.baykal.edumyclient.ui.screen.account.profile

import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.base.ui.BaseViewModel
import com.baykal.edumyclient.data.domain.account.GetUserUseCase
import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val session: EdumySession,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState = _uiState.asStateFlow()

    fun getUserInformation() {
        args?.getString(ProfileRoute.USER_ID)?.let { userId ->
            if (userId == ProfileRoute.DEFAULT) {
                session.withUser { user ->
                    _uiState.update { it.copy(user = user, currentUser = true) }
                }
            } else {
                getUserUseCase.observe(userId).collect { user ->
                    _uiState.update { it.copy(user = user, currentUser = false) }
                }
            }
        }
    }

    fun logout() {
        session.drop()
        navigate(LoginRoute.route, true)
    }
}