package com.example.ajarin.message.domain.use_cases

import com.example.ajarin.core.domain.model.Message
import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.message.domain.repository.MessageRepository

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