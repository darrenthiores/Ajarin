package com.example.ajarin.android.withdraw.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.BorderTextField
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.withdraw.presentation.components.BankAccountField
import com.example.ajarin.android.withdraw.presentation.components.WithdrawBankAccountSheet
import com.example.ajarin.android.withdraw.presentation.helper.priceFilter
import com.example.ajarin.withdraw.presentation.WithdrawEvent
import com.example.ajarin.withdraw.presentation.WithdrawState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WithdrawScreen(
    state: WithdrawState,
    onEvent: (WithdrawEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        topBar = {
            CommonHeader(
                title = "Withdraw",
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            Layout(
                content = {
                    PrimaryButton(
                        modifier = Modifier
                            .padding(horizontal = 24.dp),
                        text = "Withdraw",
                        textModifier = Modifier
                            .fillMaxWidth()
                    ) {
                        onEvent(
                            WithdrawEvent.Withdraw
                        )
                    }
                }
            ) { measurable, constraints ->
                val placeable = measurable.first().measure(constraints)

                layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, -placeable.height)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        sheetContent = {
            WithdrawBankAccountSheet(
                accounts = state.bankAccounts,
                onClick = {
                    onEvent(
                        WithdrawEvent.OnSelectAccount(it)
                    )

                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                },
                onBackClick = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        scaffoldState = scaffoldState
    ) { paddingValue ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    text = "Bank Selection",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                BankAccountField(
                    account = state.selectedAccount
                ) {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            }

            item {
                Divider()
            }

            item {
                Text(
                    text = "SEND AMOUNT",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = state.amount,
                    onValueChange = {
                        onEvent(
                            WithdrawEvent.OnAmountChange(it)
                        )
                    },
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .heightIn(min = 50.dp),
                    decorationBox = { innerTextField ->
                        if(state.amount.isEmpty()) {
                            Text(
                                text = "Rp0",
                                style = MaterialTheme.typography.h3.copy(
                                    color = Color.LightGray
                                )
                            )
                        }

                        Row {
                            if (state.amount.isNotEmpty()) {
                                Text(
                                    text = "Rp",
                                    style = MaterialTheme.typography.h3
                                )
                            }

                            innerTextField()
                        }
                    },
                    textStyle = MaterialTheme.typography.h3,
                    visualTransformation = {
                        priceFilter(it.text)
                    }
                )

                state.amountError?.let {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = it.message ?: "Validation Error",
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }

            item {
                BorderTextField(
                    text = state.note,
                    placeholder = "Write a note",
                    onTextChange = {
                        onEvent(
                            WithdrawEvent.OnNoteChange(it)
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun WithdrawScreenPreview() {
    AjarinTheme {
        WithdrawScreen(
            state = WithdrawState(
                amount = "1000"
            ),
            onEvent = {  }
        ) {

        }
    }
}