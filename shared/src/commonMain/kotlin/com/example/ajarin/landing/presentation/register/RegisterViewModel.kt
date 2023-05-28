package com.example.ajarin.landing.presentation.register

import com.example.ajarin.core.domain.utils.toCommonFlow
import com.example.ajarin.core.domain.utils.toCommonStateFlow
import com.example.ajarin.core.utils.UiEvent
import com.example.ajarin.core.utils.errors.ValidationError
import com.example.ajarin.landing.domain.use_cases.LandingUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val landingUseCases: LandingUseCases,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    email = event.email
                )

                val isValid = landingUseCases.validateEmail(email = event.email)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        emailError = null
                    )

                    _state.value = state.value.copy(
                        registerEnabled = isRegisterEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        emailError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        registerEnabled = isRegisterEnabled()
                    )
                }
            }
            is RegisterEvent.OnNameChange -> {
                _state.value = state.value.copy(
                    name = event.name
                )

                _state.value = state.value.copy(
                    registerEnabled = isRegisterEnabled()
                )
            }
            is RegisterEvent.OnNumberChange -> {
                _state.value = state.value.copy(
                    number = event.number,
                    sendOtpError = null
                )

                val isValid = landingUseCases.validateNumber(number = event.number)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        numberError = null
                    )

                    _state.value = state.value.copy(
                        sendOtpEnabled = isSendOtpEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        numberError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        sendOtpEnabled = isSendOtpEnabled()
                    )
                }
            }
            is RegisterEvent.OnPasswordChange -> {
                _state.value = state.value.copy(
                    password = event.password
                )

                val isValid = landingUseCases.validatePassword(password = event.password)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        passwordError = null
                    )

                    _state.value = state.value.copy(
                        registerEnabled = isRegisterEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        passwordError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        registerEnabled = isRegisterEnabled()
                    )
                }
            }
            is RegisterEvent.OnOtpChange -> {
                _state.value = state.value.copy(
                    otp = event.otp,
                    verifyOtpError = null
                )

                _state.value = state.value.copy(
                    verifyOtpEnabled = isVerifyOtpEnabled()
                )
            }
            is RegisterEvent.OnSendOtpResult -> {
                _state.value = state.value.copy(
                    sendOtpError = null,
                    sendOtpLoading = false,
                    currentSection = RegisterSection.NumberVerification,
                    verificationId = null
                )
            }
            RegisterEvent.OnVerifyOtp -> {
                _state.value = state.value.copy(
                    registerSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            RegisterEvent.Register -> {

            }
            is RegisterEvent.UpdateSection -> {
                _state.value = state.value.copy(
                    currentSection = event.section
                )
            }
            RegisterEvent.ToggleShowPassword -> {
                _state.value = state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
        }
    }

    private fun isRegisterEnabled(): Boolean {
        return state.value.name.isNotEmpty()
                && state.value.nameError == null
                && state.value.email.isNotEmpty()
                && state.value.emailError == null
                && state.value.password.isNotEmpty()
                && state.value.passwordError == null
    }

    private fun isSendOtpEnabled(): Boolean {
        return state.value.number.isNotEmpty()
                && state.value.numberError == null
                && !state.value.sendOtpLoading
                && state.value.sendOtpError == null
    }

    private fun isVerifyOtpEnabled(): Boolean {
        return state.value.otp.isNotEmpty()
                && state.value.otpError == null
                && !state.value.verifyOtpLoading
                && state.value.verifyOtpError == null
    }
}