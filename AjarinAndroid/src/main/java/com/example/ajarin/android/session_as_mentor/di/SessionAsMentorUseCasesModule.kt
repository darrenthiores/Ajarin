package com.example.ajarin.android.session_as_mentor.di

import com.example.ajarin.sessionAsMentor.domain.use_cases.GetUserById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SessionAsMentorUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetUserByIdUseCase(): GetUserById {
        return GetUserById()
    }
}