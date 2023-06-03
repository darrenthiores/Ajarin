package com.example.ajarin.inbox.presentation.model

import com.example.ajarin.home.presentation.Mentor

data class UiMessage(
    val inboxId: String,
    val lastMessage: String,
    val mentor: Mentor
)
