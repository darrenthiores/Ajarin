package com.example.ajarin.history.presentation

import com.example.ajarin.core.domain.utils.toCommonStateFlow
import com.example.ajarin.history.domain.use_cases.GetHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

class HistoryViewModel(
    getHistory: GetHistory,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.toCommonStateFlow()

    init {
        _state.value = state.value.copy(
            historySessions = getHistory.execute()
        )
    }
}