package com.example.ajarin.data.participants.local

import com.example.ajarin.data.participants.local.dao.ParticipantDao

class ParticipantLocalDataSource(
    private val dao: ParticipantDao
) {
    suspend fun addParticipant(
        userId: String,
        inboxId: String
    ) {
        dao.addParticipant(
            userId = userId,
            inboxId = inboxId
        )
    }
}