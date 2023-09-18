package com.example.ajarin.presentation.addPin

sealed class AddPinEvent {
    data class OnPinChange(val pin: String): AddPinEvent()
    object AddPin: AddPinEvent()
}