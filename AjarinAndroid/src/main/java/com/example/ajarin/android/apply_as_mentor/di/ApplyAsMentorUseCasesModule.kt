package com.example.ajarin.android.apply_as_mentor.di

import com.example.ajarin.applyAsMentor.domain.use_cases.ValidateId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ApplyAsMentorUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideValidateIdUseCase(): ValidateId {
        return ValidateId()
    }
}