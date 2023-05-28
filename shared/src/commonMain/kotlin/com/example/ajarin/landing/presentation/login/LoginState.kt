package com.example.ajarin.landing.presentation.login

import com.example.ajarin.core.utils.errors.ValidationError

data class LoginState(
    val email: String = "",
    val emailError: ValidationError? = null,
    val password: String = "",
    val passwordError: ValidationError? = null,
    val isPasswordVisible: Boolean = false,
    val loginButtonEnabled: Boolean = false,
    val loginError: String? = "",
    val loginSuccess: Boolean = false
)