package com.example.ajarin.android.di.inbox

import com.example.ajarin.data.inbox.local.InboxLocalDataSource
import com.example.ajarin.data.inbox.local.dao.InboxChatDao
import com.example.ajarin.data.inbox.local.dao.SqlDelightInboxChatDao
import com.example.ajarin.database.ChatDatabase
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
            dao = chatDao
        )
    }
}