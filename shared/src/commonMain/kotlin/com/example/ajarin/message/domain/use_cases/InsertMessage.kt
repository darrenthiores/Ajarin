package com.example.ajarin.message.domain.use_cases

import com.example.ajarin.message.domain.repository.MessageRepository

class InsertMessage(
    private val repository: MessageRepository
) {
    suspend fun execute(
        inboxId: String,
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        repository.insertMessage(
            inboxId = inboxId,
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }
}