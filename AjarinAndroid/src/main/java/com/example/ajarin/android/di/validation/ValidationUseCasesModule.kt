package com.example.ajarin.android.di.validation

import com.example.ajarin.domain.validation.use_cases.ValidateEmail
import com.example.ajarin.domain.validation.use_cases.ValidateId
import com.example.ajarin.domain.validation.use_cases.ValidateNumber
import com.example.ajarin.domain.validation.use_cases.ValidatePassword
import com.example.ajarin.domain.validation.use_cases.ValidationUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ValidationUseCasesModule {

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
    fun provideValidateIdUseCase(): ValidateId {
        return ValidateId()
    }

    @Provides
    @ViewModelScoped
    fun provideLandingUseCases(): ValidationUseCases {
        return ValidationUseCases(
            validateNumber = ValidateNumber(),
            validatePassword = ValidatePassword(),
            validateEmail = ValidateEmail(),
            validateId = ValidateId()
        )
    }
}