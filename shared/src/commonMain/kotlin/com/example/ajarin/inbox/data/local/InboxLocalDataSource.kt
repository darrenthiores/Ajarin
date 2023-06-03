package com.example.ajarin.inbox.data.local

import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.core.domain.utils.toCommonFlow
import com.example.ajarin.inbox.data.local.dao.InboxChatDao
import com.example.ajarin.inbox.domain.model.Inbox

class InboxLocalDataSource(
    private val inboxChatDao: InboxChatDao
) {
    fun getInbox(
        userId: String
    ): CommonFlow<List<Inbox>> {
        return inboxChatDao
            .getInbox(
                userId = userId
            )
            .toCommonFlow()
    }
}