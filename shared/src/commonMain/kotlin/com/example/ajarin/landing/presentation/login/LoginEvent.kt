package com.example.ajarin.landing.presentation.login

sealed class LoginEvent {
    data class OnEmailChange(val email: String): LoginEvent()
    data class OnPasswordChange(val password: String): LoginEvent()
    object ToggleShowPassword: LoginEvent()
    object Login: LoginEvent()
}