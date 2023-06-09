package com.example.ajarin.android.bank_account.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.bank_account.presentation.components.BankAccountItem
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.bankAccount.presentation.BankAccountState
import com.example.ajarin.bankAccount.presentation.dummyAccount

@Composable
fun BankAccountScreen(
    state: BankAccountState,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit
) {
    val cardOffset = with(LocalDensity.current) { 96.dp.toPx() }

    Scaffold(
        topBar = {
            CommonHeader(
                title = "Bank Accounts",
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add Bank Account"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            itemsIndexed(
                items = state.bankAccounts,
                key = { _, account -> account.id }
            ) { index, account ->
                BankAccountItem(
                    account = account,
                    cardOffset = cardOffset
                )

                if (index != state.bankAccounts.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Preview
@Composable
private fun BankAccountScreenPreview() {
    AjarinTheme {
        BankAccountScreen(
            state = BankAccountState(
                bankAccounts = dummyAccount
            ),
            onBackClick = {  }
        ) {

        }
    }
}