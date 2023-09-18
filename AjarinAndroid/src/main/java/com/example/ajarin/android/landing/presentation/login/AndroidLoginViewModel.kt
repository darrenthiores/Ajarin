package com.example.ajarin.android.landing.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.domain.auth.use_cases.Login
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.domain.validation.use_cases.ValidateEmail
import com.example.ajarin.domain.validation.use_cases.ValidatePassword
import com.example.ajarin.presentation.login.LoginEvent
import com.example.ajarin.presentation.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AndroidLoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val login: Login,
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        LoginViewModel(
            validateEmail = validateEmail,
            validatePassword = validatePassword,
            login = login,
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