package com.example.ajarin.android.di.message

import com.example.ajarin.data.message.local.MessageLocalDataSource
import com.example.ajarin.data.message.repository.MessageRepositoryImpl
import com.example.ajarin.domain.message.repository.MessageRepository
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