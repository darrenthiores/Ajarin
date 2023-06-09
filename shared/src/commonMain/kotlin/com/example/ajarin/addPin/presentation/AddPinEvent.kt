package com.example.ajarin.addPin.presentation

sealed class AddPinEvent {
    data class OnPinChange(val pin: String): AddPinEvent()
    object AddPin: AddPinEvent()
}