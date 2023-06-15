package com.example.ajarin.withdraw.presentation

import com.example.ajarin.bankAccount.presentation.BankAccount

sealed class WithdrawEvent {
    data class OnAmountChange(val amount: String): WithdrawEvent()
    data class OnSelectAccount(val bankAccount: BankAccount): WithdrawEvent()
    data class OnNoteChange(val note: String): WithdrawEvent()
    object Withdraw: WithdrawEvent()
}
