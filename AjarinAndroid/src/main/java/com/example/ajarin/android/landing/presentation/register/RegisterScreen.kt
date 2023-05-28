package com.example.ajarin.android.landing.presentation.register

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.ajarin.android.landing.presentation.register.sections.CreateAccount
import com.example.ajarin.android.landing.presentation.register.sections.FillNumber
import com.example.ajarin.android.landing.presentation.register.sections.NumberVerification
import com.example.ajarin.landing.presentation.register.RegisterEvent
import com.example.ajarin.landing.presentation.register.RegisterSection
import com.example.ajarin.landing.presentation.register.RegisterState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onSignIn: () -> Unit
) {
    var isBack by remember {
        mutableStateOf(false)
    }

    AnimatedContent(
        targetState = state.currentSection,
        modifier = Modifier
            .fillMaxSize(),
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = {
                    if (isBack) -it else it
                }
            ) with slideOutHorizontally(
                targetOffsetX = {
                    if (isBack) it else -it
                }
            )
        }
    ) { section ->
        when (section) {
            RegisterSection.CreateAccount -> {
                CreateAccount(
                    state = state,
                    onEvent = { event ->
                        isBack = false
                        onEvent(event)
                    },
                    onSignIn = onSignIn
                )
            }
            RegisterSection.FillNumber -> {
                FillNumber(
                    state = state,
                    onEvent = { event ->
                        if (event is RegisterEvent.UpdateSection) {
                            isBack = event.section == RegisterSection.CreateAccount
                        }

                        onEvent(event)
                    }
                )
            }
            RegisterSection.NumberVerification -> {
                NumberVerification(
                    state = state,
                    onEvent = { event ->
                        if (event is RegisterEvent.UpdateSection) {
                            isBack = event.section == RegisterSection.FillNumber
                        }

                        if (event is RegisterEvent.OnVerifyOtp) {
                            isBack = false
                        }

                        onEvent(event)
                    }
                )
            }
        }
    }
}