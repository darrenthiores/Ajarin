package com.example.ajarin.message.domain.use_cases

import com.example.ajarin.message.domain.repository.MessageRepository

class CreateInbox(
    private val repository: MessageRepository
) {
    suspend fun execute(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        repository.createInbox(
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }
}