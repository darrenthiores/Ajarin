package com.example.ajarin.presentation.addBank

import com.example.ajarin.presentation.bankAccount.Bank
import com.example.ajarin.domain.validation.utils.ValidationError

data class AddBankState(
    val selectedBank: Bank? = null,
    val selectedBankError: ValidationError? = null,
    val isBankDropDownOpen: Boolean = false,
    val accountNumber: String = "",
    val accountNumberError: ValidationError? = null,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false
)
