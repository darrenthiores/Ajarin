package com.example.ajarin.home.data.local.dao

import kotlinx.coroutines.flow.Flow

interface HomeChatDao {
    fun getUnreadCount(
        userId: String
    ): Flow<Int>
}