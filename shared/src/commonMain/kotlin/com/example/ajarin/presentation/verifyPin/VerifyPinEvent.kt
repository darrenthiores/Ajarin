package com.example.ajarin.presentation.verifyPin

sealed class VerifyPinEvent {
    data class OnPinChange(val pin: String): VerifyPinEvent()
    object VerifyPin: VerifyPinEvent()
}