package com.example.ajarin.android.di.core

import com.example.ajarin.android.core.data.repository.CoreRepositoryImpl
import com.example.ajarin.android.core.domain.repository.CoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreRepositoryModule {
    @Binds
    abstract fun provideRepository(repository: CoreRepositoryImpl): CoreRepository
}