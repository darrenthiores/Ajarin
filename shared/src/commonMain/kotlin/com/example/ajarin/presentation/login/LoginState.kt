package com.example.ajarin.presentation.login

import com.example.ajarin.domain.validation.utils.ValidationError

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