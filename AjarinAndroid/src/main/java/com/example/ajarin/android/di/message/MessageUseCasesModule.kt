package com.example.ajarin.android.di.message

import com.example.ajarin.domain.inbox.repository.InboxRepository
import com.example.ajarin.domain.message.repository.MessageRepository
import com.example.ajarin.domain.message.use_cases.GetMessagesById
import com.example.ajarin.domain.message.use_cases.GetUnreadCount
import com.example.ajarin.domain.message.use_cases.InsertMessage
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
    fun provideGetUnreadCountUseCase(
        repository: MessageRepository
    ): GetUnreadCount {
        return GetUnreadCount(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideInsertMessageUseCase(
        repository: MessageRepository,
        inboxRepository: InboxRepository
    ): InsertMessage {
        return InsertMessage(
            repository = repository,
            inboxRepository = inboxRepository
        )
    }
}