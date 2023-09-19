package com.example.ajarin.android.di.user

import com.example.ajarin.data.user.remote.UserRemoteDataSource
import com.example.ajarin.data.user.repository.UserRepositoryImpl
import com.example.ajarin.domain.user.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        remoteDataSource: UserRemoteDataSource
    ): UserRepository {
        return UserRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}