package com.example.ajarin.data.message.local

import com.example.ajarin.data.message.local.dao.MessageDao
import com.example.ajarin.data.message.mapper.toMessage
import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.message.models.Message
import kotlinx.coroutines.flow.map

class MessageLocalDataSource(
    private val dao: MessageDao
) {
    fun getUnreadCount(
        userId: String
    ): CommonFlow<Int> {
        return dao
            .getUnreadCount(
                userId = userId
            )
            .toCommonFlow()
    }

    fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): CommonFlow<List<Message>> {
        return dao
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

    suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        dao.insertMessage(
            inboxId = inboxId,
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }
}