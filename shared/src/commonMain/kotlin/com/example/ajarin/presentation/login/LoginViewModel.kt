package com.example.ajarin.presentation.login

import com.example.ajarin.domain.auth.use_cases.Login
import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.domain.validation.use_cases.ValidateEmail
import com.example.ajarin.domain.validation.use_cases.ValidatePassword
import com.example.ajarin.domain.validation.utils.ValidationError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val login: Login,
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
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        loginSuccess = true
                    )

                    viewModelScope.launch {
                        _uiEvent.send(
                            UiEvent.Success
                        )
                    }

                    return@launch

                    val result = login(
                        email = state.value.email,
                        password = state.value.password
                    )

                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                loginError = result.message
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                loginSuccess = true
                            )

                            viewModelScope.launch {
                                _uiEvent.send(
                                    UiEvent.Success
                                )
                            }
                        }
                    }
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