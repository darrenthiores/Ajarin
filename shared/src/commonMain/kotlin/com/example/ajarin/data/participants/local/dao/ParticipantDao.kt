package com.example.ajarin.data.participants.local.dao

interface ParticipantDao {
    suspend fun addParticipant(
        userId: String,
        inboxId: String
    )
}