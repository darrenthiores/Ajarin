package com.example.ajarin.android.landing.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.core.utils.UiEvent
import com.example.ajarin.landing.domain.use_cases.ValidateEmail
import com.example.ajarin.landing.domain.use_cases.ValidatePassword
import com.example.ajarin.landing.presentation.login.LoginEvent
import com.example.ajarin.landing.presentation.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AndroidLoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        LoginViewModel(
            validateEmail = validateEmail,
            validatePassword = validatePassword,
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

    fun onEvent(event: LoginEvent) {
        viewModel.onEvent(event)
    }
}