package com.example.ajarin.android.history.di

import com.example.ajarin.history.domain.use_cases.GetHistory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HistoryUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetHistoryUseCase(): GetHistory {
        return GetHistory()
    }
}