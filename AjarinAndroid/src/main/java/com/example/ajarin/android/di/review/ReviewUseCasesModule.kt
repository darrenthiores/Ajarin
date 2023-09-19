package com.example.ajarin.android.di.review

import com.example.ajarin.domain.review.repository.ReviewRepository
import com.example.ajarin.domain.review.use_cases.CreateReview
import com.example.ajarin.domain.review.use_cases.GetMentorReviews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ReviewUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideMentorReviewsUseCase(
        repository: ReviewRepository
    ): GetMentorReviews {
        return GetMentorReviews(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideCreateReviewUseCase(
        repository: ReviewRepository
    ): CreateReview {
        return CreateReview(
            repository = repository
        )
    }
}