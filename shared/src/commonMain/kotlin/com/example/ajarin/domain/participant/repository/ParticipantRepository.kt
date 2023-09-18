package com.example.ajarin.domain.participant.repository

interface ParticipantRepository {
    suspend fun addParticipant(
        userId: String,
        inboxId: String
    )
}