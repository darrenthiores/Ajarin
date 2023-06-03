package com.example.ajarin.core.data.mapper

import com.example.ajarin.core.domain.model.Message
import database.ChatEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun ChatEntity.toMessage(): Message {
    return Message(
        id = id,
        inboxId = inboxId,
        sentToId = sentToId,
        sentFromId = sentFromId,
        content = content,
        mediaUrl = mediaUrl,
        date = Instant.fromEpochMilliseconds(timestamp).toLocalDateTime(TimeZone.currentSystemDefault()),
        isRead = isRead
    )
}