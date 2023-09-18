package com.example.ajarin.android.di.auth

import com.example.ajarin.data.auth.remote.AuthRemoteDataSource
import com.example.ajarin.data.auth.remote.service.AuthService
import com.example.ajarin.data.auth.remote.service.KtorAuthService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRemoteModule {
    @Provides
    @Singleton
    fun provideAuthService(
        client: HttpClient
    ): AuthService {
        return KtorAuthService(client)
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        service: AuthService,
        dispatchers: DispatchersProvider
    ): AuthRemoteDataSource {
        return AuthRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}