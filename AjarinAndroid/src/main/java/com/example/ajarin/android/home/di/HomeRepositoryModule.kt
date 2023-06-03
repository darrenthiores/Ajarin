package com.example.ajarin.android.home.di

import com.example.ajarin.home.data.local.HomeLocalDataSource
import com.example.ajarin.home.data.repository.HomeRepositoryImpl
import com.example.ajarin.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        localDataSource: HomeLocalDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(
            localDataSource = localDataSource
        )
    }
}