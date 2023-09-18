package com.example.ajarin.presentation.message

sealed class MessageEvent {
    data class OnMessageChange(val newText: String): MessageEvent()
    data class SendMessage(val userId: String): MessageEvent()
}
