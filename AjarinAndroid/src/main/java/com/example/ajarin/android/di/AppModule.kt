package com.example.ajarin.android.di

import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import com.example.ajarin.domain.core.dispatchers.StandardDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersProvider {
        return StandardDispatchers()
    }
}