package com.example.ajarin.android.home.di

import com.example.ajarin.home.domain.use_cases.SearchMentorByCourse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideSearchMentorByCourseUseCase(): SearchMentorByCourse {
        return SearchMentorByCourse()
    }
}