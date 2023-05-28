package com.example.ajarin.landing.presentation.login

import com.example.ajarin.core.domain.utils.toCommonFlow
import com.example.ajarin.core.domain.utils.toCommonStateFlow
import com.example.ajarin.core.utils.UiEvent
import com.example.ajarin.core.utils.errors.ValidationError
import com.example.ajarin.landing.domain.use_cases.ValidateEmail
import com.example.ajarin.landing.domain.use_cases.ValidatePassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(LoginState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            LoginEvent.Login -> {
                _state.value = state.value.copy(
                    loginSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            is LoginEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    email = event.email,
                    loginError = null
                )

                val isValid = validateEmail(email = event.email)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        emailError = null
                    )

                    _state.value = state.value.copy(
                        loginButtonEnabled = isButtonEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        emailError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        loginButtonEnabled = isButtonEnabled()
                    )
                }
            }
            is LoginEvent.OnPasswordChange -> {
                _state.value = state.value.copy(
                    password = event.password,
                    loginError = null
                )

                val isValid = validatePassword(password = event.password)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        passwordError = null
                    )

                    _state.value = state.value.copy(
                        loginButtonEnabled = isButtonEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        passwordError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        loginButtonEnabled = isButtonEnabled()
                    )
                }
            }
            LoginEvent.ToggleShowPassword -> {
                _state.value = state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
        }
    }

    private fun isButtonEnabled(): Boolean {
        return state.value.email.isNotEmpty()
                && state.value.emailError == null
                && state.value.password.isNotEmpty()
                && state.value.passwordError == null
                && state.value.loginError == null
    }
}