package com.example.ajarin.android.di.user

import com.example.ajarin.domain.user.repository.UserRepository
import com.example.ajarin.domain.user.use_cases.GetUser
import com.example.ajarin.domain.user.use_cases.GetUserById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserUseCasesModule {
    @Provides
    @ViewModelScoped
    fun provideGetUserUseCase(
        repository: UserRepository
    ): GetUser {
        return GetUser(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetUserByIdUseCase(
        repository: UserRepository
    ): GetUserById {
        return GetUserById(
            repository = repository
        )
    }
}