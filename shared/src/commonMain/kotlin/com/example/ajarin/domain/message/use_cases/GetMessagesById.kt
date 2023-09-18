package com.example.ajarin.domain.message.use_cases

import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.message.models.Message
import com.example.ajarin.domain.message.repository.MessageRepository

class GetMessagesById(
    private val repository: MessageRepository
) {
    fun execute(
        sentToId: String,
        sentFromId: String
    ): CommonFlow<List<Message>> {
        return repository.getMessagesById(
            sentToId = sentToId,
            sentFromId = sentFromId
        )
    }
}