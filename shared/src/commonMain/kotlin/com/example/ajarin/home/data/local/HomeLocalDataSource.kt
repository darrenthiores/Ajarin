package com.example.ajarin.home.data.local

import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.core.domain.utils.toCommonFlow
import com.example.ajarin.home.data.local.dao.HomeChatDao

class HomeLocalDataSource(
    private val chatDao: HomeChatDao
) {
    fun getUnreadCount(
        userId: String
    ): CommonFlow<Int> {
        return chatDao
            .getUnreadCount(
                userId = userId
            )
            .toCommonFlow()
    }
}