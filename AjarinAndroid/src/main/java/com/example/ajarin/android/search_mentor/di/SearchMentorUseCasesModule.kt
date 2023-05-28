package com.example.ajarin.android.search_mentor.di

import com.example.ajarin.searchMentor.domain.use_cases.SearchMentor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SearchMentorUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideSearchMentorUseCase(): SearchMentor {
        return SearchMentor()
    }
}