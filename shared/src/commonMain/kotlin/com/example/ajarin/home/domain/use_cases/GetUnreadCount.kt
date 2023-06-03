package com.example.ajarin.home.domain.use_cases

import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.home.domain.repository.HomeRepository

class GetUnreadCount(
    private val repository: HomeRepository
) {
    fun execute(
        userId: String
    ): CommonFlow<Int> {
        return repository.getUnreadCount(
            userId = userId
        )
    }
}