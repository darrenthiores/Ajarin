package com.example.ajarin.android.verify_pin.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.add_pin.presentation.components.PinTextField
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.verifyPin.presentation.VerifyPinEvent
import com.example.ajarin.verifyPin.presentation.VerifyPinState

@Composable
fun VerifyPinScreen(
    state: VerifyPinState,
    onEvent: (VerifyPinEvent) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "Verify Pin",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Your Pin",
                style = MaterialTheme.typography.h5
            )

            Text(
                text = "Please enter a pin to complete your withdrawal",
                style = MaterialTheme.typography.body2
            )

            PinTextField(
                pinText = state.pin,
                error = state.pinError,
                onPinChange = {
                    onEvent(
                        VerifyPinEvent.OnPinChange(it)
                    )
                }
            )

            PrimaryButton(
                text = "Verify Pin",
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = state.pin.isNotEmpty() &&
                        state.pin.length == 6 &&
                        state.pinError == null &&
                        !state.verifyPinLoading
            ) {
                onEvent(
                    VerifyPinEvent.VerifyPin
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddPinScreenPreview() {
    AjarinTheme {
        VerifyPinScreen(
            state = VerifyPinState(),
            onEvent = {  }
        ) {

        }
    }
}