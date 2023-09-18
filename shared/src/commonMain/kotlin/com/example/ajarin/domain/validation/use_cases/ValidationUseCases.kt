package com.example.ajarin.domain.validation.use_cases

data class ValidationUseCases(
    val validateNumber: ValidateNumber,
    val validatePassword: ValidatePassword,
    val validateEmail: ValidateEmail,
    val validateId: ValidateId
)
