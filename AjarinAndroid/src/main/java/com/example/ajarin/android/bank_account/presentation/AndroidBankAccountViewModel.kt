package com.example.ajarin.android.bank_account.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.bankAccount.presentation.BankAccountViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidBankAccountViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {
    private val viewModel by lazy {
        BankAccountViewModel(
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    var hasPin by mutableStateOf(true)

    fun init() {
        viewModel.init()
        hasPin = preferences.loadHasPin()
    }
}