package com.example.ajarin.android.di.core.network

import com.example.ajarin.data.core.remote.client.HttpClientFactory
import com.example.ajarin.domain.core.preferences.TokenPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Provides
    @Singleton
    fun provideClient(
        tokenPreferences: TokenPreferences
    ): HttpClient {
        return HttpClientFactory()
            .create(
                tokenPreferences = tokenPreferences
            )
    }
}