package com.example.ajarin.android.landing.presentation.register.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.landing.presentation.register.components.OtpTextField
import com.example.ajarin.android.landing.presentation.register.components.RegisterHeader
import com.example.ajarin.presentation.register.RegisterEvent
import com.example.ajarin.presentation.register.RegisterSection
import com.example.ajarin.presentation.register.RegisterState

@Composable
fun NumberVerification(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {
    Scaffold(
        topBar = {
            RegisterHeader(
                currentSection = RegisterSection.NumberVerification,
                onBack = {
                    onEvent(
                        RegisterEvent.UpdateSection(RegisterSection.FillNumber)
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
                painter = painterResource(id = R.drawable.register_otp),
                contentDescription = "OTP Icon",
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
                    text = "Verification code",
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Please enter the OTP we sent to WhatsApp ${state.number}",
                    style = MaterialTheme.typography.body2.copy(
                        color = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OtpTextField(
                otpText = state.otp,
                onOtpTextChange = {
                    onEvent(
                        RegisterEvent.OnOtpChange(it)
                    )
                },
                error = state.otpError
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = "SUBMIT",
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = state.verifyOtpEnabled
            ) {
                onEvent(
                    RegisterEvent.OnVerifyOtp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Didn’t receive the OTP?",
                    style = MaterialTheme.typography.body2.copy(
                        color = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = "Resend",
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.primary
                    ),
                    modifier = Modifier
                        .clickable {

                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun NumberVerificationPreview() {
    AjarinTheme {
        NumberVerification(
            state = RegisterState(),
            onEvent = {  }
        )
    }
}