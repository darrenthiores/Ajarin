package com.example.ajarin.android.landing.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.domain.auth.use_cases.Register
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.domain.validation.use_cases.ValidationUseCases
import com.example.ajarin.presentation.register.RegisterEvent
import com.example.ajarin.presentation.register.RegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AndroidRegisterViewModel @Inject constructor(
    private val validationUseCases: ValidationUseCases,
    private val register: Register,
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        RegisterViewModel(
            validationUseCases = validationUseCases,
            register = register,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent
        .onEach {
            if (it == UiEvent.Success) {
                preferences.saveShouldShowOnBoarding(
                    shouldShow = false
                )
            }
        }

    fun onEvent(event: RegisterEvent) {
        viewModel.onEvent(event)
    }
}