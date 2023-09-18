package com.example.ajarin.data.inbox.local.dao

import com.example.ajarin.domain.inbox.model.Inbox
import kotlinx.coroutines.flow.Flow

interface InboxChatDao {
    fun getInbox(userId: String): Flow<List<Inbox>>

    suspend fun createInbox(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ): String

    suspend fun updateInbox(
        inboxId: String,
        sentFromId: String,
        message: String,
    )
}