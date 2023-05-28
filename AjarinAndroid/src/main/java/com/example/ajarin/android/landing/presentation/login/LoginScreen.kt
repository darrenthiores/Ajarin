package com.example.ajarin.android.landing.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.BasicTextField
import com.example.ajarin.android.core_ui.components.PasswordTextField
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.landing.presentation.login.LoginEvent
import com.example.ajarin.landing.presentation.login.LoginState

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onSignUp: () -> Unit
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
                text = "Login",
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Login to your account and restore all your data.",
                style = MaterialTheme.typography.body2.copy(
                    color = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            label = "Email",
            text = state.email,
            onTextChange = {
                onEvent(
                    LoginEvent.OnEmailChange(it)
                )
            },
            error = state.emailError
        )

        Spacer(modifier = Modifier.height(8.dp))

        PasswordTextField(
            password = state.password,
            onPasswordChange = {
                onEvent(
                    LoginEvent.OnPasswordChange(it)
                )
            },
            passwordVisible = state.isPasswordVisible,
            showPassword = {
                onEvent(
                    LoginEvent.ToggleShowPassword
                )
            },
            error = state.passwordError
        )

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryButton(
            text = "LOGIN",
            textModifier = Modifier
                .fillMaxWidth()
        ) {
            onEvent(
                LoginEvent.Login
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account?",
                style = MaterialTheme.typography.body2.copy(
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.primary
                ),
                modifier = Modifier
                    .clickable {
                        onSignUp()
                    }
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    AjarinTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = {  },
            onSignUp = {  }
        )
    }
}