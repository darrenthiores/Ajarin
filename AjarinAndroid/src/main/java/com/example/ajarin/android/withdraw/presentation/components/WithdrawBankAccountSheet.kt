package com.example.ajarin.android.withdraw.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.bankAccount.BankAccount
import com.example.ajarin.presentation.bankAccount.dummyAccount

@Composable
fun WithdrawBankAccountSheet(
    modifier: Modifier = Modifier,
    accounts: List<BankAccount>,
    onClick: (BankAccount) -> Unit,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            CommonHeader(
                title = "Select Account",
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        itemsIndexed(
            items = accounts,
            key = { _, account -> account.id }
        ) { index, account ->
            BankAccountField(
                account = account,
                onClick = {
                    onClick(account)
                }
            )

            if (index != accounts.lastIndex) {
                Divider()
            }
        }
    }
}

@Preview
@Composable
private fun WithdrawBankAccountPreview() {
    AjarinTheme {
        WithdrawBankAccountSheet(
            accounts = dummyAccount,
            onClick = {  }
        ) {

        }
    }
}