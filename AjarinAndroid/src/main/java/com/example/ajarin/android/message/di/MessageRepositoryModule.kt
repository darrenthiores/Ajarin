package com.example.ajarin.android.message.di

import com.example.ajarin.message.data.local.MessageLocalDataSource
import com.example.ajarin.message.data.repository.MessageRepositoryImpl
import com.example.ajarin.message.domain.repository.MessageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessageRepositoryModule {

    @Provides
    @Singleton
    fun provideMessageRepository(
        localDataSource: MessageLocalDataSource
    ): MessageRepository {
        return MessageRepositoryImpl(
            localDataSource = localDataSource
        )
    }
}