package com.example.ajarin.addPin.presentation

import com.example.ajarin.core.utils.errors.ValidationError

data class AddPinState(
    val pin: String = "",
    val pinError: ValidationError? = null,
    val addPinLoading: Boolean = false,
    val addPinSuccess: Boolean = false
)
