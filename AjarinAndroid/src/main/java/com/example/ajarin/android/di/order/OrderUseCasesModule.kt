package com.example.ajarin.android.di.order

import com.example.ajarin.domain.order.use_cases.GetHistory
import com.example.ajarin.domain.order.use_cases.GetSessionById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OrderUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetHistoryUseCase(): GetHistory {
        return GetHistory()
    }

    @Provides
    @ViewModelScoped
    fun provideGetSessionByIdUseCase(): GetSessionById {
        return GetSessionById()
    }
}