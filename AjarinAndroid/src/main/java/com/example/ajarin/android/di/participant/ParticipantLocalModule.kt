package com.example.ajarin.android.di.participant

import com.example.ajarin.data.participants.local.ParticipantLocalDataSource
import com.example.ajarin.data.participants.local.dao.ParticipantDao
import com.example.ajarin.data.participants.local.dao.SqlDelightParticipantDao
import com.example.ajarin.database.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ParticipantLocalModule {
    @Provides
    @Singleton
    fun provideParticipantDao(
        database: ChatDatabase
    ): ParticipantDao {
        return SqlDelightParticipantDao(database)
    }

    @Provides
    @Singleton
    fun provideMessageLocalDataSource(
        dao: ParticipantDao
    ): ParticipantLocalDataSource {
        return ParticipantLocalDataSource(
            dao = dao
        )
    }
}