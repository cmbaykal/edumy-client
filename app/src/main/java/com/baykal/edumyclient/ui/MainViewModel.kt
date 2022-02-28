package com.baykal.edumyclient.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val uiState = MutableStateFlow(MainState())

    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    fun setMainState(visibility: Boolean) {
        uiValue = uiValue.copy(bottomBarVisibility = visibility)
    }

    fun setDialog(visibility: Boolean) {
        uiValue = uiValue.copy(topBarVisibility = visibility)
    }

}