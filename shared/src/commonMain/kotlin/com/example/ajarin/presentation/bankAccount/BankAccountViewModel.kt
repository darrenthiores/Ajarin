package com.example.ajarin.presentation.bankAccount

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BankAccountViewModel(
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(BankAccountState())
    val state = _state.toCommonStateFlow()

    fun init() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                bankAccounts = dummyAccount
            )
        }
    }
}