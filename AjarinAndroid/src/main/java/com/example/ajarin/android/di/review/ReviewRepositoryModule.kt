package com.example.ajarin.android.di.review

import com.example.ajarin.data.review.remote.ReviewRemoteDataSource
import com.example.ajarin.data.review.repository.ReviewRepositoryImpl
import com.example.ajarin.domain.review.repository.ReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReviewRepositoryModule {

    @Provides
    @Singleton
    fun provideReviewRepository(
        remoteDataSource: ReviewRemoteDataSource
    ): ReviewRepository {
        return ReviewRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}