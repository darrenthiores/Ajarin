package com.example.ajarin.android.landing.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.core.utils.UiEvent
import com.example.ajarin.landing.domain.use_cases.LandingUseCases
import com.example.ajarin.landing.presentation.register.RegisterEvent
import com.example.ajarin.landing.presentation.register.RegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AndroidRegisterViewModel @Inject constructor(
    private val landingUseCases: LandingUseCases,
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        RegisterViewModel(
            landingUseCases = landingUseCases,
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