package com.example.ajarin.android.add_bank.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.addBank.presentation.AddBankEvent
import com.example.ajarin.addBank.presentation.AddBankViewModel

class AndroidAddBankViewModel: ViewModel() {
    private val viewModel by lazy {
        AddBankViewModel(
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    fun onEvent(event: AddBankEvent) {
        viewModel.onEvent(event)
    }
}