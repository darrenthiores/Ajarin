package com.example.ajarin.android.inbox.di

import com.example.ajarin.database.ChatDatabase
import com.example.ajarin.inbox.data.local.InboxLocalDataSource
import com.example.ajarin.inbox.data.local.dao.InboxChatDao
import com.example.ajarin.inbox.data.local.dao.SqlDelightInboxChatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InboxLocalModule {
    @Provides
    @Singleton
    fun provideInboxChatDao(
        database: ChatDatabase
    ): InboxChatDao {
        return SqlDelightInboxChatDao(database)
    }

    @Provides
    @Singleton
    fun provideInboxLocalDataSource(
        chatDao: InboxChatDao
    ): InboxLocalDataSource {
        return InboxLocalDataSource(
            inboxChatDao = chatDao
        )
    }
}