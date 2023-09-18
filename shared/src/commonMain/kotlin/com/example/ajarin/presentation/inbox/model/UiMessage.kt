package com.example.ajarin.presentation.inbox.model

import com.example.ajarin.presentation.home.Mentor

data class UiMessage(
    val inboxId: String,
    val lastMessage: String,
    val mentor: Mentor
)
