package com.example.ajarin.core.domain.model

import kotlinx.datetime.LocalDateTime

data class Message(
    val id: String,
    val sentToId: String,
    val sentFromId: String,
    val content: String,
    val mediaUrl: String,
    val date: LocalDateTime,
    val isRead: Boolean
)
