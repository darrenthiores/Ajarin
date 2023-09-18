package com.example.ajarin.presentation.addBank

import com.example.ajarin.presentation.bankAccount.Bank

sealed class AddBankEvent {
    data class OnAccountNumberChange(val number: String): AddBankEvent()
    data class SelectBank(val bank: Bank): AddBankEvent()
    data class ToggleBankDropDown(val isOpen: Boolean): AddBankEvent()
    object AddBank: AddBankEvent()
}
