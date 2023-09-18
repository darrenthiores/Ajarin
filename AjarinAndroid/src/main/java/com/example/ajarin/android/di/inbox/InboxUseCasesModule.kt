package com.example.ajarin.android.di.inbox

import com.example.ajarin.domain.inbox.repository.InboxRepository
import com.example.ajarin.domain.inbox.use_cases.CreateInbox
import com.example.ajarin.domain.inbox.use_cases.GetInbox
import com.example.ajarin.domain.message.repository.MessageRepository
import com.example.ajarin.domain.participant.repository.ParticipantRepository
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

    @Provides
    @ViewModelScoped
    fun provideCreateInboxUseCase(
        repository: MessageRepository,
        inboxRepository: InboxRepository,
        participantRepository: ParticipantRepository
    ): CreateInbox {
        return CreateInbox(
            messageRepository = repository,
            inboxRepository = inboxRepository,
            participantRepository = participantRepository
        )
    }

}