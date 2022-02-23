package com.baykal.edumyclient.ui.screen.account.login

import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.component.InputState
import com.baykal.edumyclient.base.nav.RouteNavigator
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {

    private val uiState = MutableStateFlow(LoginState())

    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    fun setEmail(state: InputState) {
        uiValue = uiValue.copy(email = state)
    }

    fun setPass(state: InputState) {
        uiValue = uiValue.copy(pass = state)
    }

    fun login() {
//        if (!uiValue.email.isError && !uiValue.pass.isError) {
        navigateToRoute(ClassroomsRoute.route, true)
//        }
    }

}