package com.example.ajarin.domain.auth.repository

import com.example.ajarin.domain.utils.Resource

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): Resource<String>

    suspend fun register(
        email: String,
        number: String,
        username: String,
        password: String
    ): Resource<String>

    suspend fun resetToken()
}