package com.example.ajarin.presentation.message

import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.message.models.Message

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
