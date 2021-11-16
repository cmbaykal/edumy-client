package com.baykal.edumyclient.base.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(viewState: ViewState, viewEvent: ViewEvent) : ViewModel() {

    private val scope = viewModelScope

    private val initialState: ViewState by lazy { setInitialState() }
    abstract fun setInitialState(): ViewState

    private val _viewState: MutableState<ViewState> = mutableStateOf(initialState)
    val viewState: State<ViewState> = _viewState

    private val _event: MutableSharedFlow<ViewEvent> = MutableSharedFlow()

    private val _effect: Channel<ViewEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    abstract fun handleEvent(event: ViewEvent)

    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        scope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    fun postState(reducer: ViewState.() -> ViewState) {
        _viewState.value = viewState.value.reducer()
    }

    fun postEffect(builder: () -> ViewEffect) {
        scope.launch {
            _effect.send(builder())
        }
    }

    fun postEvent(event: ViewEvent) {
        scope.launch {
            _event.emit(event)
        }
    }

}