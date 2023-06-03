package com.example.ajarin.inbox.domain.model

data class Inbox(
    val id: String,
    val lastMessage: String,
    val lastSendUserId: String,
    val participantId: String
)
