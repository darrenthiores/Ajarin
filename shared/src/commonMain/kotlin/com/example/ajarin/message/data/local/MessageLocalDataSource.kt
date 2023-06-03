package com.example.ajarin.message.data.local

import com.example.ajarin.core.data.mapper.toMessage
import com.example.ajarin.core.domain.model.Message
import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.core.domain.utils.toCommonFlow
import com.example.ajarin.message.data.local.dao.MessageChatDao
import kotlinx.coroutines.flow.map

class MessageLocalDataSource(
    private val chatDao: MessageChatDao
) {
    fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): CommonFlow<List<Message>> {
        return chatDao
            .getMessagesById(
                sentToId = sentToId,
                sentFromId = sentFromId
            )
            .map { listOfMessage ->
                listOfMessage.map { message ->
                    message.toMessage()
                }
            }
            .toCommonFlow()
    }

    suspend fun createInbox(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        chatDao.createInbox(
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }

    suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        chatDao.insertMessage(
            inboxId = inboxId,
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }
}