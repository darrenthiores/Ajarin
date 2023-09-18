package com.example.ajarin.presentation.addPin

import com.example.ajarin.domain.validation.utils.ValidationError

data class AddPinState(
    val pin: String = "",
    val pinError: ValidationError? = null,
    val addPinLoading: Boolean = false,
    val addPinSuccess: Boolean = false
)
