package com.baykal.edumyclient.ui

import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    session: EdumySession
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    init {
        session.withUserId {
            _mainState.update { it.copy(loggedIn = true) }
        } ?: run {
            _mainState.update { it.copy(loggedIn = false) }
        }
    }
}