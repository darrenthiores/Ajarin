package com.example.ajarin.android.session.di

import com.example.ajarin.session.domain.use_cases.GetSessionById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SessionUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetSessionByIdUseCase(): GetSessionById {
        return GetSessionById()
    }
}