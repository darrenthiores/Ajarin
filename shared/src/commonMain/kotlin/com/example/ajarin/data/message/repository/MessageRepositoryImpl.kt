package com.example.ajarin.data.message.repository

import com.example.ajarin.data.message.local.MessageLocalDataSource
import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.message.models.Message
import com.example.ajarin.domain.message.repository.MessageRepository

class MessageRepositoryImpl(
    private val localDataSource: MessageLocalDataSource
): MessageRepository {
    override fun getUnreadCount(
        userId: String
    ): CommonFlow<Int> {
        return localDataSource.getUnreadCount(
            userId = userId
        )
    }

    override fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): CommonFlow<List<Message>> {
        return localDataSource.getMessagesById(
            sentToId = sentToId,
            sentFromId = sentFromId
        )
    }

    override suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String,
    ) {
        localDataSource.insertMessage(
            inboxId = inboxId,
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }
}