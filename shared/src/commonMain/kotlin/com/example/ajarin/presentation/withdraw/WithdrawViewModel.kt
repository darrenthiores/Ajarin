package com.example.ajarin.presentation.withdraw

import com.example.ajarin.presentation.bankAccount.dummyAccount
import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.domain.validation.utils.ValidationError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WithdrawViewModel(
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(WithdrawState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: WithdrawEvent) {
        when(event) {
            is WithdrawEvent.OnAmountChange -> {
                if ((event.amount.toIntOrNull() ?: 0) > (state.value.walletBalance.toIntOrNull() ?: 0)) {
                    _state.value = state.value.copy(
                        amount = event.amount,
                        amountError = ValidationError(
                            message = "You cannot withdraw more than balance in your wallet"
                        )
                    )

                    return
                }

                if ((event.amount.toIntOrNull() ?: 0) < 10000) {
                    _state.value = state.value.copy(
                        amount = event.amount,
                        amountError = ValidationError(
                            message = "You cannot withdraw less than Rp. 10.000"
                        )
                    )

                    return
                }

                _state.value = state.value.copy(
                    amount = event.amount,
                    amountError = null
                )
            }
            is WithdrawEvent.OnNoteChange -> {
                _state.value = state.value.copy(
                    note = event.note,
                    noteError = null
                )
            }
            is WithdrawEvent.OnSelectAccount -> {
                _state.value = state.value.copy(
                    selectedAccount = event.bankAccount
                )
            }
            is WithdrawEvent.UpdateWithdrawResult -> {
                _state.value = state.value.copy(
                    withdrawSuccess = event.result
                )
            }
            WithdrawEvent.Withdraw -> {
                _state.value = state.value.copy(
                    withdrawSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                bankAccounts = dummyAccount
            )
        }
    }
}