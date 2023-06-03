package com.example.ajarin.android.inbox.di

import com.example.ajarin.inbox.domain.repository.InboxRepository
import com.example.ajarin.inbox.domain.use_cases.GetInbox
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InboxUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetInboxUseCase(
        repository: InboxRepository
    ): GetInbox {
        return GetInbox(
            repository = repository
        )
    }
}