package com.example.ajarin.android.add_pin.presentation

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
import com.example.ajarin.addPin.presentation.AddPinEvent
import com.example.ajarin.addPin.presentation.AddPinState
import com.example.ajarin.android.add_pin.presentation.components.PinTextField
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun AddPinScreen(
    state: AddPinState,
    onEvent: (AddPinEvent) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "Add Pin",
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
                text = "Please enter a pin to secure your wallet",
                style = MaterialTheme.typography.body2
            )

            PinTextField(
                pinText = state.pin,
                error = state.pinError,
                onPinChange = {
                    onEvent(
                        AddPinEvent.OnPinChange(it)
                    )
                }
            )

            PrimaryButton(
                text = "Save Pin",
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = state.pin.isNotEmpty() &&
                        state.pin.length == 6 &&
                        state.pinError == null &&
                        !state.addPinLoading
            ) {
                onEvent(
                    AddPinEvent.AddPin
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddPinScreenPreview() {
    AjarinTheme {
        AddPinScreen(
            state = AddPinState(),
            onEvent = {  }
        ) {

        }
    }
}