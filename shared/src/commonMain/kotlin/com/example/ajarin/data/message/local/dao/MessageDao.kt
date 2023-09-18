package com.example.ajarin.data.message.local.dao

import database.ChatEntity
import kotlinx.coroutines.flow.Flow

interface MessageDao {
    fun getUnreadCount(
        userId: String
    ): Flow<Int>

    fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): Flow<List<ChatEntity>>

    suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    )
}