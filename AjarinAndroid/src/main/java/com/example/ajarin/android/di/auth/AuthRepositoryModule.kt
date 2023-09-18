package com.example.ajarin.android.di.auth

import com.example.ajarin.data.auth.remote.AuthRemoteDataSource
import com.example.ajarin.data.auth.repository.AuthRepositoryImpl
import com.example.ajarin.domain.auth.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        remoteDataSource: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}