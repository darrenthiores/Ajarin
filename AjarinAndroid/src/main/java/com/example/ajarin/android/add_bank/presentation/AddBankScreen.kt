package com.example.ajarin.android.add_bank.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.presentation.addBank.AddBankEvent
import com.example.ajarin.presentation.addBank.AddBankState
import com.example.ajarin.android.add_bank.presentation.components.BankDropDown
import com.example.ajarin.android.core_ui.components.BasicTextField
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun AddBankScreen(
    state: AddBankState,
    onEvent: (AddBankEvent) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "Add Bank Account",
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            PrimaryButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "Add",
                textModifier = Modifier
                    .fillMaxWidth()
            ) {
                onEvent(
                    AddBankEvent.AddBank
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Bank Selection",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                BankDropDown(
                    bank = state.selectedBank,
                    isOpen = state.isBankDropDownOpen,
                    onClick = {
                        onEvent(
                            AddBankEvent.ToggleBankDropDown(!state.isBankDropDownOpen)
                        )
                    },
                    onDismiss = {
                        onEvent(
                            AddBankEvent.ToggleBankDropDown(false)
                        )
                    },
                    onSelectBank = {
                        onEvent(
                            AddBankEvent.SelectBank(it)
                        )

                        onEvent(
                            AddBankEvent.ToggleBankDropDown(false)
                        )
                    }
                )
            }

            item {
                Text(
                    text = "Bank Account Number",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    label = "Bank Account Number",
                    text = state.accountNumber,
                    onTextChange = {
                        onEvent(
                            AddBankEvent.OnAccountNumberChange(it)
                        )
                    },
                    error = state.selectedBankError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddBankScreenPreview() {
    AjarinTheme {
        AddBankScreen(
            state = AddBankState(),
            onEvent = {  }
        ) {

        }
    }
}