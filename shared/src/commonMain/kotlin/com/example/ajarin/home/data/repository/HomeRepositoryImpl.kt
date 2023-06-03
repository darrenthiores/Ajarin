package com.example.ajarin.home.data.repository

import com.example.ajarin.core.domain.utils.CommonFlow
import com.example.ajarin.home.data.local.HomeLocalDataSource
import com.example.ajarin.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val localDataSource: HomeLocalDataSource
): HomeRepository {
    override fun getUnreadCount(
        userId: String
    ): CommonFlow<Int> {
        return localDataSource.getUnreadCount(
            userId = userId
        )
    }
}