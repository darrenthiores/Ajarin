package com.example.ajarin.presentation.inbox

import com.example.ajarin.presentation.inbox.model.UiMessage

data class InboxState(
    val inbox: List<UiMessage> = emptyList(),
    val isLoading: Boolean = false
)
