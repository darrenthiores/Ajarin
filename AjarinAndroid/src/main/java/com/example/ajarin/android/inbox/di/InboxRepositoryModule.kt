package com.example.ajarin.android.inbox.di

import com.example.ajarin.inbox.data.local.InboxLocalDataSource
import com.example.ajarin.inbox.data.repository.InboxRepositoryImpl
import com.example.ajarin.inbox.domain.repository.InboxRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InboxRepositoryModule {

    @Provides
    @Singleton
    fun provideInboxRepository(
        localDataSource: InboxLocalDataSource
    ): InboxRepository {
        return InboxRepositoryImpl(
            localDataSource = localDataSource
        )
    }
}