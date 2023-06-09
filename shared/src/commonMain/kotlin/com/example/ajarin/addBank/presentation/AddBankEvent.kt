package com.example.ajarin.addBank.presentation

import com.example.ajarin.bankAccount.presentation.Bank

sealed class AddBankEvent {
    data class OnAccountNumberChange(val number: String): AddBankEvent()
    data class SelectBank(val bank: Bank): AddBankEvent()
    data class ToggleBankDropDown(val isOpen: Boolean): AddBankEvent()
    object AddBank: AddBankEvent()
}
