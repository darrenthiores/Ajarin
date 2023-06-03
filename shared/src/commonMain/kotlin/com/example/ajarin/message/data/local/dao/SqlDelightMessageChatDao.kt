package com.example.ajarin.message.data.local.dao

import com.example.ajarin.core.utils.randomUUID
import com.example.ajarin.database.ChatDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.ChatEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

class SqlDelightMessageChatDao(
    db: ChatDatabase
): MessageChatDao {
    private val inboxQueries = db.inboxQueries
    private val participantQueries = db.participantQueries
    private val queries = db.chatQueries

    override fun getMessagesById(
        sentToId:String,
        sentFromId: String
    ): Flow<List<ChatEntity>> {
        return queries
            .getMessagesById(
                sentToId = sentToId,
                sentFromId = sentFromId,
                sentToId_ = sentFromId,
                sentFromId_ = sentToId
            )
            .asFlow()
            .mapToList()
    }

    override suspend fun createInbox(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        val inboxId = randomUUID()
        inboxQueries.createInbox(
            id = inboxId,
            lastMessage = message,
            lastSendUserId = sentFromId
        )

        participantQueries.addParticipant(
            id = null,
            userId = sentToId,
            inboxId = inboxId
        )

        participantQueries.addParticipant(
            id = null,
            userId = sentFromId,
            inboxId = inboxId
        )

        queries.insertMessage(
            id = randomUUID(),
            inboxId = inboxId,
            sentToId = sentToId,
            sentFromId = sentFromId,
            content = message,
            mediaUrl = mediaUrl,
            timestamp = Clock.System.now().toEpochMilliseconds(),
            isRead = false
        )
    }

    override suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        inboxQueries.createInbox(
            id = inboxId,
            lastMessage = message,
            lastSendUserId = sentFromId
        )

        queries.insertMessage(
            id = randomUUID(),
            inboxId = inboxId,
            sentToId = sentToId,
            sentFromId = sentFromId,
            content = message,
            mediaUrl = mediaUrl,
            timestamp = Clock.System.now().toEpochMilliseconds(),
            isRead = false
        )
    }
}