package com.example.ajarin.android.landing.presentation.register.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.core_ui.components.BasicTextField
import com.example.ajarin.android.core_ui.components.PasswordTextField
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.landing.presentation.register.RegisterEvent
import com.example.ajarin.landing.presentation.register.RegisterSection
import com.example.ajarin.landing.presentation.register.RegisterState

@Composable
fun CreateAccount(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Create an account to access all features in this app.",
                style = MaterialTheme.typography.body2.copy(
                    color = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            label = "Username",
            text = state.name,
            onTextChange = {
                onEvent(
                    RegisterEvent.OnNameChange(it)
                )
            },
            error = state.nameError
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            label = "Email",
            text = state.email,
            onTextChange = {
                onEvent(
                    RegisterEvent.OnEmailChange(it)
                )
            },
            error = state.emailError
        )

        Spacer(modifier = Modifier.height(8.dp))

        PasswordTextField(
            password = state.password,
            onPasswordChange = {
                onEvent(
                    RegisterEvent.OnPasswordChange(it)
                )
            },
            passwordVisible = state.isPasswordVisible,
            showPassword = {
                onEvent(
                    RegisterEvent.ToggleShowPassword
                )
            },
            error = state.passwordError
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Next",
            textModifier = Modifier
                .fillMaxWidth(),
            isEnabled = state.registerEnabled
        ) {
            onEvent(
                RegisterEvent.UpdateSection(RegisterSection.FillNumber)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.body2.copy(
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "Sign In",
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.primary
                ),
                modifier = Modifier
                    .clickable {
                        onSignIn()
                    }
            )
        }
    }
}

@Preview
@Composable
fun CreateAccountPreview() {
    AjarinTheme {
        CreateAccount(
            state = RegisterState(),
            onEvent = {  },
            onSignIn = {  }
        )
    }
}