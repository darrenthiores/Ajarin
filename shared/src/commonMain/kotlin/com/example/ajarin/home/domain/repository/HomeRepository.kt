package com.example.ajarin.home.domain.repository

import com.example.ajarin.core.domain.utils.CommonFlow

interface HomeRepository {
    fun getUnreadCount(
        userId: String
    ): CommonFlow<Int>
}