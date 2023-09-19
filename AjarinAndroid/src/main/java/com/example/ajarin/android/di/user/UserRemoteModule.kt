package com.example.ajarin.android.di.user

import com.example.ajarin.data.user.remote.UserRemoteDataSource
import com.example.ajarin.data.user.remote.service.KtorUserService
import com.example.ajarin.data.user.remote.service.UserService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRemoteModule {
    @Provides
    @Singleton
    fun provideUserService(
        client: HttpClient
    ): UserService {
        return KtorUserService(client)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        service: UserService,
        dispatchers: DispatchersProvider
    ): UserRemoteDataSource {
        return UserRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}