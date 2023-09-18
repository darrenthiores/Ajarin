package com.example.ajarin.android.di.message

import com.example.ajarin.data.message.local.MessageLocalDataSource
import com.example.ajarin.data.message.local.dao.MessageDao
import com.example.ajarin.data.message.local.dao.SqlDelightMessageDao
import com.example.ajarin.database.ChatDatabase
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
    ): MessageDao {
        return SqlDelightMessageDao(database)
    }

    @Provides
    @Singleton
    fun provideMessageLocalDataSource(
        chatDao: MessageDao
    ): MessageLocalDataSource {
        return MessageLocalDataSource(
            dao = chatDao
        )
    }
}