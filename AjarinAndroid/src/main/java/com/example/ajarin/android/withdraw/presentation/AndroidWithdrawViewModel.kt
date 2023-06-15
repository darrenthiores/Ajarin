package com.example.ajarin.android.withdraw.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.withdraw.presentation.WithdrawEvent
import com.example.ajarin.withdraw.presentation.WithdrawViewModel

class AndroidWithdrawViewModel: ViewModel() {
    private val viewModel by lazy {
        WithdrawViewModel(
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    fun onEvent(event: WithdrawEvent) {
        viewModel.onEvent(event)
    }
}