package com.example.ajarin.inbox.domain.repository

import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.inbox.domain.model.Inbox

interface InboxRepository {
    fun getInbox(
        userId: String
    ): CommonFlow<List<Inbox>>
}