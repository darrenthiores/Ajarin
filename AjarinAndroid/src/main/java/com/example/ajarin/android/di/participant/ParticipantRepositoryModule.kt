package com.example.ajarin.android.di.participant

import com.example.ajarin.data.participants.local.ParticipantLocalDataSource
import com.example.ajarin.data.participants.repository.ParticipantRepositoryImpl
import com.example.ajarin.domain.participant.repository.ParticipantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ParticipantRepositoryModule {

    @Provides
    @Singleton
    fun provideMessageRepository(
        localDataSource: ParticipantLocalDataSource
    ): ParticipantRepository {
        return ParticipantRepositoryImpl(
            localDataSource = localDataSource
        )
    }
}