package com.example.ajarin.inbox.presentation

import com.example.ajarin.inbox.presentation.model.UiMessage

data class InboxState(
    val inbox: List<UiMessage> = emptyList(),
    val isLoading: Boolean = false
)
