package com.example.ajarin.data.auth.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String
)
