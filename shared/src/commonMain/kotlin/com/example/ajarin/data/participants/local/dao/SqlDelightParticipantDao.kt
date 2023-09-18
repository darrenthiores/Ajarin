package com.example.ajarin.data.participants.local.dao

import com.example.ajarin.database.ChatDatabase

class SqlDelightParticipantDao(
    db: ChatDatabase
): ParticipantDao {
    private val queries = db.participantQueries

    override suspend fun addParticipant(userId: String, inboxId: String) {
        queries.addParticipant(
            id = null,
            userId = userId,
            inboxId = inboxId
        )
    }
}