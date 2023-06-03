package com.example.ajarin.android.message.di

import com.example.ajarin.message.domain.repository.MessageRepository
import com.example.ajarin.message.domain.use_cases.CreateInbox
import com.example.ajarin.message.domain.use_cases.GetMessagesById
import com.example.ajarin.message.domain.use_cases.InsertMessage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MessageUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetMessageByIdUseCase(
        repository: MessageRepository
    ): GetMessagesById {
        return GetMessagesById(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideCreateInboxUseCase(
        repository: MessageRepository
    ): CreateInbox {
        return CreateInbox(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideInsertMessageUseCase(
        repository: MessageRepository
    ): InsertMessage {
        return InsertMessage(
            repository = repository
        )
    }
}