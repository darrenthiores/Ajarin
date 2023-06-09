package com.example.ajarin.addBank.presentation

import com.example.ajarin.bankAccount.presentation.Bank
import com.example.ajarin.core.utils.errors.ValidationError

data class AddBankState(
    val selectedBank: Bank? = null,
    val selectedBankError: ValidationError? = null,
    val isBankDropDownOpen: Boolean = false,
    val accountNumber: String = "",
    val accountNumberError: ValidationError? = null,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false
)
