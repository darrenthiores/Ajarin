package com.example.ajarin.message.domain.repository

import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.core.domain.model.Message

interface MessageRepository {
    fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): CommonFlow<List<Message>>

    suspend fun createInbox(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    )

    suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    )
}