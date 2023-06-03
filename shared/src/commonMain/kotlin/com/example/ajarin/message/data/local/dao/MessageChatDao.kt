package com.example.ajarin.message.data.local.dao

import database.ChatEntity
import kotlinx.coroutines.flow.Flow

interface MessageChatDao {
    fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): Flow<List<ChatEntity>>

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