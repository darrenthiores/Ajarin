package com.example.ajarin.presentation.history

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

class HistoryViewModel(
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnUpdateIsMentor -> {
                _state.value = state.value.copy(
                    isMentor = event.isMentor
                )
            }
        }
    }
}