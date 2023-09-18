package com.example.ajarin.domain.inbox.use_cases

import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.inbox.model.Inbox
import com.example.ajarin.domain.inbox.repository.InboxRepository

class GetInbox(
    private val repository: InboxRepository
) {
    fun execute(
        userId: String
    ): CommonFlow<List<Inbox>> {
        return repository.getInbox(
            userId = userId
        )
    }
}