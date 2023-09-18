package com.example.ajarin.data.inbox.local.dao

import com.example.ajarin.database.ChatDatabase
import com.example.ajarin.domain.inbox.model.Inbox
import com.example.ajarin.domain.utils.randomUUID
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightInboxChatDao(
    db: ChatDatabase
): InboxChatDao {
    private val queries = db.inboxQueries

    override fun getInbox(
        userId: String
    ): Flow<List<Inbox>> {
        return queries
            .getInbox(
                userId = userId
            )
            .asFlow()
            .mapToList()
            .map { result ->
                result.map {
                    Inbox(
                        id = it.id,
                        lastMessage = it.lastMessage,
                        lastSendUserId = it.lastSendUserId,
                        participantId = it.userId
                    )
                }
            }
    }

    override suspend fun createInbox(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ): String {
        val inboxId = randomUUID()
        queries.createInbox(
            id = inboxId,
            lastMessage = message,
            lastSendUserId = sentFromId
        )

        return inboxId
    }

    override suspend fun updateInbox(inboxId: String, sentFromId: String, message: String) {
        queries.createInbox(
            id = inboxId,
            lastMessage = message,
            lastSendUserId = sentFromId
        )
    }
}