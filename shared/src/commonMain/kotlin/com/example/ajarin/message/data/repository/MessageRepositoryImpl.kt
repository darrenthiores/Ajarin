package com.example.ajarin.message.data.repository

import com.example.ajarin.core.domain.model.Message
import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.message.data.local.MessageLocalDataSource
import com.example.ajarin.message.domain.repository.MessageRepository

class MessageRepositoryImpl(
    private val localDataSource: MessageLocalDataSource
): MessageRepository {
    override fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): CommonFlow<List<Message>> {
        return localDataSource.getMessagesById(
            sentToId = sentToId,
            sentFromId = sentFromId
        )
    }

    override suspend fun createInbox(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        localDataSource.createInbox(
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
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