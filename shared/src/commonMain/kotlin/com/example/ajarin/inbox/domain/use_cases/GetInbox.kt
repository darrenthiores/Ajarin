package com.example.ajarin.inbox.domain.use_cases

import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.inbox.domain.model.Inbox
import com.example.ajarin.inbox.domain.repository.InboxRepository

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