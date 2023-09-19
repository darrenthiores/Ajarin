package com.example.ajarin.android.di.mentor

import com.example.ajarin.data.mentor.remote.MentorRemoteDataSource
import com.example.ajarin.data.mentor.remote.service.KtorMentorService
import com.example.ajarin.data.mentor.remote.service.MentorService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MentorRemoteModule {
    @Provides
    @Singleton
    fun provideMentorService(
        client: HttpClient
    ): MentorService {
        return KtorMentorService(client)
    }

    @Provides
    @Singleton
    fun provideMentorRemoteDataSource(
        service: MentorService,
        dispatchers: DispatchersProvider
    ): MentorRemoteDataSource {
        return MentorRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}