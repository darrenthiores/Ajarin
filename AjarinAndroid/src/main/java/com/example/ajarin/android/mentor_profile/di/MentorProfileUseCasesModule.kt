package com.example.ajarin.android.mentor_profile.di

import com.example.ajarin.mentorProfile.domain.use_cases.GetMentorById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MentorProfileUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetMentorByIdUseCase(): GetMentorById {
        return GetMentorById()
    }
}