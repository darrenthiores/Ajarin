package com.example.ajarin.data.auth.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val username: String,
    val password: String,
    val number: String
)
