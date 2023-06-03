package com.example.ajarin.inbox.data.local.dao

import com.example.ajarin.database.ChatDatabase
import com.example.ajarin.inbox.domain.model.Inbox
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
}