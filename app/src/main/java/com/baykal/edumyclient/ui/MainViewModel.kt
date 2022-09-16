package com.baykal.edumyclient.ui

import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    session: EdumySession
) : ViewModel() {

    val mainState = MutableStateFlow(MainState())

    init {
        session.withUserId {
            mainState.update { it.copy(startRoute = ClassroomsRoute.route) }
        } ?: run {
            mainState.update { it.copy(startRoute = LoginRoute.route) }
        }
    }
}