package com.example.ajarin.data.inbox.repository

import com.example.ajarin.data.inbox.local.InboxLocalDataSource
import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.inbox.model.Inbox
import com.example.ajarin.domain.inbox.repository.InboxRepository

class InboxRepositoryImpl(
    private val localDataSource: InboxLocalDataSource
): InboxRepository {
    override fun getInbox(
        userId: String
    ): CommonFlow<List<Inbox>> {
        return localDataSource.getInbox(
            userId = userId
        )
    }

    override suspend fun createInbox(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ): String {
        return localDataSource.createInbox(
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }

    override suspend fun updateInbox(inboxId: String, sentFromId: String, message: String) {
        localDataSource.updateInbox(
            inboxId = inboxId,
            message = message,
            sentFromId = sentFromId
        )
    }
}