package com.example.ajarin.android.di.mentor

import com.example.ajarin.data.mentor.remote.MentorRemoteDataSource
import com.example.ajarin.data.mentor.repository.MentorRepositoryImpl
import com.example.ajarin.domain.mentor.repository.MentorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MentorRepositoryModule {

    @Provides
    @Singleton
    fun provideMentorRepository(
        remoteDataSource: MentorRemoteDataSource
    ): MentorRepository {
        return MentorRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}