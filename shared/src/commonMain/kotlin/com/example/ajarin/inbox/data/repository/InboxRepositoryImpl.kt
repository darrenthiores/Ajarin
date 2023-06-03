package com.example.ajarin.inbox.data.repository

import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.inbox.data.local.InboxLocalDataSource
import com.example.ajarin.inbox.domain.model.Inbox
import com.example.ajarin.inbox.domain.repository.InboxRepository

class InboxRepositoryImpl(
    private val localDataSource: InboxLocalDataSource
): InboxRepository {
    override fun getInbox(
        userId: String
    ): CommonFlow<List<Inbox>> {
        return localDataSource.getInbox(
            userId = userId
        )
    }
}