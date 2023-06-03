package com.example.ajarin.android.home.di

import com.example.ajarin.database.ChatDatabase
import com.example.ajarin.home.data.local.HomeLocalDataSource
import com.example.ajarin.home.data.local.dao.HomeChatDao
import com.example.ajarin.home.data.local.dao.SqlDelightHomeChatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeLocalModule {
    @Provides
    @Singleton
    fun provideHomeChatDao(
        database: ChatDatabase
    ): HomeChatDao {
        return SqlDelightHomeChatDao(database)
    }

    @Provides
    @Singleton
    fun provideHomeLocalDataSource(
        chatDao: HomeChatDao
    ): HomeLocalDataSource {
        return HomeLocalDataSource(
            chatDao = chatDao
        )
    }
}