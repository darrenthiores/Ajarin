package com.example.ajarin.android.di.review

import com.example.ajarin.data.review.remote.ReviewRemoteDataSource
import com.example.ajarin.data.review.remote.service.KtorReviewService
import com.example.ajarin.data.review.remote.service.ReviewService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReviewRemoteModule {
    @Provides
    @Singleton
    fun provideReviewService(
        client: HttpClient
    ): ReviewService {
        return KtorReviewService(client)
    }

    @Provides
    @Singleton
    fun provideReviewRemoteDataSource(
        service: ReviewService,
        dispatchers: DispatchersProvider
    ): ReviewRemoteDataSource {
        return ReviewRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}