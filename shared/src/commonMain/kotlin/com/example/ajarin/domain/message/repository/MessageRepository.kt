package com.example.ajarin.domain.message.repository

import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.message.models.Message

interface MessageRepository {
    fun getUnreadCount(
        userId: String
    ): CommonFlow<Int>

    fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): CommonFlow<List<Message>>

    suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    )
}