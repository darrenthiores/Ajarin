package com.example.ajarin.domain.inbox.repository

import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.inbox.model.Inbox

interface InboxRepository {
    fun getInbox(
        userId: String
    ): CommonFlow<List<Inbox>>

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