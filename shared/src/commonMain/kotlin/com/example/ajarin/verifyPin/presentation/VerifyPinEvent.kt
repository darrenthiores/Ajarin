package com.example.ajarin.verifyPin.presentation

sealed class VerifyPinEvent {
    data class OnPinChange(val pin: String): VerifyPinEvent()
    object VerifyPin: VerifyPinEvent()
}