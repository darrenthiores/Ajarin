package com.example.ajarin.data.core.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val id: String,
    val time: String // format hh:MM
)