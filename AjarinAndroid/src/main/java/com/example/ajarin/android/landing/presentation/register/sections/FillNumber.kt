package com.example.ajarin.android.landing.presentation.register.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.components.BasicTextField
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.landing.presentation.register.components.RegisterHeader
import com.example.ajarin.core.utils.Resource
import com.example.ajarin.landing.presentation.register.RegisterEvent
import com.example.ajarin.landing.presentation.register.RegisterSection
import com.example.ajarin.landing.presentation.register.RegisterState

@Composable
fun FillNumber(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {
    Scaffold(
        topBar = {
            RegisterHeader(
                currentSection = RegisterSection.FillNumber,
                onBack = {
                    onEvent(
                        RegisterEvent.UpdateSection(RegisterSection.CreateAccount)
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.register_phone),
                contentDescription = "Phone Icon",
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Fill in your phone number",
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Please enter the phone number we will send the OTP in this phone number.",
                    style = MaterialTheme.typography.body2.copy(
                        color = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                label = "Phone Number",
                text = state.number,
                onTextChange = {
                    onEvent(
                        RegisterEvent.OnNumberChange(it)
                    )
                },
                error = state.numberError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = "GET OTP",
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = state.sendOtpEnabled
            ) {
                onEvent(
                    RegisterEvent.OnSendOtpResult(
                        Resource.Success("")
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun FillNumberPreview() {
    AjarinTheme {
        FillNumber(
            state = RegisterState(),
            onEvent = {  }
        )
    }
}