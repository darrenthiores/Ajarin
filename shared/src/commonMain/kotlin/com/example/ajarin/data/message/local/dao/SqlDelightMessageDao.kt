package com.example.ajarin.data.message.local.dao

import com.example.ajarin.database.ChatDatabase
import com.example.ajarin.domain.utils.randomUUID
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrDefault
import database.ChatEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightMessageDao(
    db: ChatDatabase
): MessageDao {
    private val queries = db.chatQueries

    override fun getUnreadCount(
        userId: String
    ): Flow<Int> {
        return queries
            .getUnreadCount(
                sentToId = userId
            )
            .asFlow()
            .mapToOneOrDefault(0)
            .map { it.toInt() }
    }

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

    override suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
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