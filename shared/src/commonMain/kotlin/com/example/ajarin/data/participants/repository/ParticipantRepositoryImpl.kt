package com.example.ajarin.data.participants.repository

import com.example.ajarin.data.participants.local.ParticipantLocalDataSource
import com.example.ajarin.domain.participant.repository.ParticipantRepository

class ParticipantRepositoryImpl(
    private val localDataSource: ParticipantLocalDataSource
): ParticipantRepository {
    override suspend fun addParticipant(userId: String, inboxId: String) {
        localDataSource.addParticipant(
            userId = userId,
            inboxId = inboxId
        )
    }
}