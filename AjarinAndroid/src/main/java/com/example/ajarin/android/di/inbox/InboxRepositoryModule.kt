package com.example.ajarin.android.di.inbox

import com.example.ajarin.data.inbox.local.InboxLocalDataSource
import com.example.ajarin.data.inbox.repository.InboxRepositoryImpl
import com.example.ajarin.domain.inbox.repository.InboxRepository
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