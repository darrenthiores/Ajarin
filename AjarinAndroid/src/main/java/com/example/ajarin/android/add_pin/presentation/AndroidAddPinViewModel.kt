package com.example.ajarin.android.add_pin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.presentation.addPin.AddPinEvent
import com.example.ajarin.presentation.addPin.AddPinViewModel
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.domain.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AndroidAddPinViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {
    private val viewModel by lazy {
        AddPinViewModel(
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent
        .onEach {
            if (it == UiEvent.Success) {
                preferences.saveHasPin(
                    hasPin = true
                )
            }
        }

    fun onEvent(event: AddPinEvent) {
        viewModel.onEvent(event)
    }
}