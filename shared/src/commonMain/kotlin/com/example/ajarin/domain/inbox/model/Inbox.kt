package com.example.ajarin.domain.inbox.model

data class Inbox(
    val id: String,
    val lastMessage: String,
    val lastSendUserId: String,
    val participantId: String
)
