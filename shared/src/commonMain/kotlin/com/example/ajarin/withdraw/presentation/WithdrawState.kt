package com.example.ajarin.withdraw.presentation

import com.example.ajarin.bankAccount.presentation.BankAccount
import com.example.ajarin.core.utils.errors.ValidationError

data class WithdrawState(
    val bankAccounts: List<BankAccount> = emptyList(),
    val walletBalance: String = "0",
    val bankAccountsLoading: Boolean = false,
    val bankAccountsError: Error? = null,
    val selectedAccount: BankAccount? = null,
    val selectedAccountError: Error? = null,
    val amount: String = "",
    val amountError: ValidationError? = null,
    val note: String = "",
    val noteError: ValidationError? = null,
    val isWithdrawing: Boolean = false,
    val withdrawSuccess: Boolean = false,
    val withdrawError: Error? = null
)
