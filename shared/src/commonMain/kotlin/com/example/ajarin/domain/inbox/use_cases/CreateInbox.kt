package com.example.ajarin.domain.inbox.use_cases

import com.example.ajarin.domain.inbox.repository.InboxRepository
import com.example.ajarin.domain.message.repository.MessageRepository
import com.example.ajarin.domain.participant.repository.ParticipantRepository

class CreateInbox(
    private val messageRepository: MessageRepository,
    private val inboxRepository: InboxRepository,
    private val participantRepository: ParticipantRepository
) {
    suspend fun execute(
        sentToId: String,
        sentFromId: String,
        message: String,
        mediaUrl: String
    ) {
        val inboxId = inboxRepository.createInbox(
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )

        participantRepository.addParticipant(
            userId = sentToId,
            inboxId = inboxId
        )

        participantRepository.addParticipant(
            userId = sentFromId,
            inboxId = inboxId
        )

        messageRepository.insertMessage(
            inboxId = inboxId,
            sentToId = sentToId,
            sentFromId = sentFromId,
            message = message,
            mediaUrl = mediaUrl
        )
    }
}