package com.baykal.edumyclient.ui

import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    session: EdumySession
) : ViewModel() {

    private val startState = MutableStateFlow("")

    var startValue
        get() = startState.value
        set(value) {
            startState.value = value
        }

    init {
        session.userId?.let {
            startValue = ClassroomsRoute.route
        } ?: run {
            startValue = LoginRoute.route
        }
    }

}