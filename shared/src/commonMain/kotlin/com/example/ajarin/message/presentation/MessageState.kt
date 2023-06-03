package com.example.ajarin.message.presentation

import com.example.ajarin.core.domain.model.Message
import com.example.ajarin.home.presentation.Mentor

data class MessageState(
    val messages: List<Message> = emptyList(),
    val messagesLoading: Boolean = false,
    val messagesError: Error? = null,
    val mentor: Mentor? = null,
    val mentorLoading: Boolean = false,
    val mentorError: Error? = null,
    val newMessage: String = "",
    val error: Error? = null,
    val isSendingMessage: Boolean = false
)
