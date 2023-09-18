package com.example.ajarin.presentation.withdraw

import com.example.ajarin.presentation.bankAccount.BankAccount

sealed class WithdrawEvent {
    data class OnAmountChange(val amount: String): WithdrawEvent()
    data class OnSelectAccount(val bankAccount: BankAccount): WithdrawEvent()
    data class OnNoteChange(val note: String): WithdrawEvent()
    object Withdraw: WithdrawEvent()
    data class UpdateWithdrawResult(val result: Boolean): WithdrawEvent()
}
