package com.example.ajarin.presentation.addPin

import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.utils.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddPinViewModel(
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(AddPinState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: AddPinEvent) {
        when(event) {
            AddPinEvent.AddPin -> {
                _state.value = state.value.copy(
                    addPinSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            is AddPinEvent.OnPinChange -> {
                _state.value = state.value.copy(
                    pin = event.pin,
                    pinError = null
                )
            }
        }
    }
}