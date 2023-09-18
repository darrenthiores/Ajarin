package com.example.ajarin.android.verify_pin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.presentation.verifyPin.VerifyPinEvent
import com.example.ajarin.presentation.verifyPin.VerifyPinViewModel

class AndroidVerifyPinViewModel : ViewModel() {
    private val viewModel by lazy {
        VerifyPinViewModel(
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    fun onEvent(event: VerifyPinEvent) {
        viewModel.onEvent(event)
    }
}