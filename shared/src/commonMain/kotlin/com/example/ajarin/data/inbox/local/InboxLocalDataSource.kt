package com.example.ajarin.data.inbox.local

import com.example.ajarin.data.inbox.local.dao.InboxChatDao
import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.inbox.model.Inbox

class InboxLocalDataSource(
    private val dao: InboxChatDao
) {
    fun getInbox(
        userId: String
    ): CommonFlow<List<Inbox>> {
        return dao
            .getInbox(
                userId = userId
            )
            .toCommonFlow()
    }

    suspend fun createInbox(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ): String {
        return dao.createInbox(
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }

    suspend fun updateInbox(inboxId: String, sentFromId: String, message: String) {
        dao.updateInbox(
            inboxId = inboxId,
            message = message,
            sentFromId = sentFromId
        )
    }
}