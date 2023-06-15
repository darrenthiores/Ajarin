package com.example.ajarin.verifyPin.presentation

import com.example.ajarin.core.utils.errors.ValidationError

data class VerifyPinState(
    val pin: String = "",
    val pinError: ValidationError? = null,
    val verifyPinLoading: Boolean = false,
    val verifyPinSuccess: Boolean = false
)
