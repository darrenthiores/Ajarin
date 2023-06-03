package com.example.ajarin.inbox.data.local.dao

import com.example.ajarin.inbox.domain.model.Inbox
import kotlinx.coroutines.flow.Flow

interface InboxChatDao {
    fun getInbox(userId: String): Flow<List<Inbox>>
}