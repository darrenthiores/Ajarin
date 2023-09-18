package com.example.ajarin.presentation.addBank

import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.utils.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddBankViewModel(
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(AddBankState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: AddBankEvent) {
        when(event) {
            is AddBankEvent.OnAccountNumberChange -> {
                _state.value = state.value.copy(
                    accountNumber = event.number
                )
            }
            is AddBankEvent.SelectBank -> {
                _state.value = state.value.copy(
                    selectedBank = event.bank
                )
            }
            is AddBankEvent.ToggleBankDropDown -> {
                _state.value = state.value.copy(
                    isBankDropDownOpen = event.isOpen
                )
            }
            AddBankEvent.AddBank -> {
                _state.value = state.value.copy(
                    saveSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
        }
    }
}