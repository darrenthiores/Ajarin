package com.example.ajarin.presentation.verifyPin

import com.example.ajarin.domain.validation.utils.ValidationError

data class VerifyPinState(
    val pin: String = "",
    val pinError: ValidationError? = null,
    val verifyPinLoading: Boolean = false,
    val verifyPinSuccess: Boolean = false
)
