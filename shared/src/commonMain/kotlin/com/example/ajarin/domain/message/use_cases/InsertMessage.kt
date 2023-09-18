package com.example.ajarin.domain.message.use_cases

import com.example.ajarin.domain.inbox.repository.InboxRepository
import com.example.ajarin.domain.message.repository.MessageRepository

class InsertMessage(
    private val repository: MessageRepository,
    private val inboxRepository: InboxRepository,
) {
    suspend fun execute(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        inboxRepository.updateInbox(
            inboxId = inboxId,
            message = message,
            sentFromId = sentFromId
        )

        repository.insertMessage(
            inboxId = inboxId,
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }
}