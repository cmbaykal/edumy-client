package com.baykal.edumyclient.ui.screen.account.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baykal.edumyclient.base.data.BaseResult.*
import com.baykal.edumyclient.base.data.SuccessResult
import com.baykal.edumyclient.data.model.account.user.RegisterCredentials
import com.baykal.edumyclient.data.service.EdumyApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val api: EdumyApi
) : ViewModel() {

    val uiState = MutableStateFlow(RegisterState())
    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    fun register(credentials: RegisterCredentials) {
        viewModelScope.launch {
            uiValue = uiValue.copy(loading = true)
            delay(1000)

            val response = api.RegisterUser(credentials)
            uiValue = if (response is SuccessResult) {
                uiValue.copy(success = true, loading = false)
            } else {
                uiValue.copy(error = true, loading = false)
            }
        }
    }
}