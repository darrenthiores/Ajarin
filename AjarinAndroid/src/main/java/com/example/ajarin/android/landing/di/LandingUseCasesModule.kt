package com.example.ajarin.android.landing.di

import com.example.ajarin.landing.domain.use_cases.LandingUseCases
import com.example.ajarin.landing.domain.use_cases.ValidateEmail
import com.example.ajarin.landing.domain.use_cases.ValidateNumber
import com.example.ajarin.landing.domain.use_cases.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LandingUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideValidateNumberUseCase(): ValidateNumber {
        return ValidateNumber()
    }

    @Provides
    @ViewModelScoped
    fun provideValidatePasswordUseCase(): ValidatePassword {
        return ValidatePassword()
    }

    @Provides
    @ViewModelScoped
    fun provideValidateEmailUseCase(): ValidateEmail {
        return ValidateEmail()
    }

    @Provides
    @ViewModelScoped
    fun provideLandingUseCases(): LandingUseCases {
        return LandingUseCases(
            validateNumber = ValidateNumber(),
            validatePassword = ValidatePassword(),
            validateEmail = ValidateEmail()
        )
    }
}