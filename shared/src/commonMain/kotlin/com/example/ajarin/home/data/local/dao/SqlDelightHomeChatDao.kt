package com.example.ajarin.home.data.local.dao

import com.example.ajarin.database.ChatDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrDefault
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightHomeChatDao(
    db: ChatDatabase
): HomeChatDao {
    private val queries = db.chatQueries

    override fun getUnreadCount(
        userId: String
    ): Flow<Int> {
        return queries
            .getUnreadCount(
                sentToId = userId
            )
            .asFlow()
            .mapToOneOrDefault(0)
            .map { it.toInt() }
    }
}