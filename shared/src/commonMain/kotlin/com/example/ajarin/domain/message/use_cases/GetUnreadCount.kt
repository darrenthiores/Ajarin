package com.example.ajarin.domain.message.use_cases

import com.example.ajarin.domain.core.utils.CommonFlow
import com.example.ajarin.domain.message.repository.MessageRepository

class GetUnreadCount(
    private val repository: MessageRepository
) {
    fun execute(
        userId: String
    ): CommonFlow<Int> {
        return repository.getUnreadCount(
            userId = userId
        )
    }
}