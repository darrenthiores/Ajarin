package com.example.ajarin.android.message.di

import com.example.ajarin.database.ChatDatabase
import com.example.ajarin.message.data.local.MessageLocalDataSource
import com.example.ajarin.message.data.local.dao.MessageChatDao
import com.example.ajarin.message.data.local.dao.SqlDelightMessageChatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessageLocalModule {
    @Provides
    @Singleton
    fun provideMessageChatDao(
        database: ChatDatabase
    ): MessageChatDao {
        return SqlDelightMessageChatDao(database)
    }

    @Provides
    @Singleton
    fun provideMessageLocalDataSource(
        chatDao: MessageChatDao
    ): MessageLocalDataSource {
        return MessageLocalDataSource(
            chatDao = chatDao
        )
    }
}