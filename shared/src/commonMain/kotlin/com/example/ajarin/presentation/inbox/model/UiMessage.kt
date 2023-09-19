package com.example.ajarin.presentation.inbox.model

import com.example.ajarin.domain.mentor.model.Mentor

data class UiMessage(
    val inboxId: String,
    val lastMessage: String,
    val mentor: Mentor
)
