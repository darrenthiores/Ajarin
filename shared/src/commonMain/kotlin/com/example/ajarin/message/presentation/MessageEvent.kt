package com.example.ajarin.message.presentation

sealed class MessageEvent {
    data class OnMessageChange(val newText: String): MessageEvent()
    data class SendMessage(val userId: String): MessageEvent()
}
