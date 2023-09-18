package com.example.ajarin.domain.auth.use_cases

import com.example.ajarin.domain.auth.repository.AuthRepository
import com.example.ajarin.domain.core.preferences.TokenPreferences
import com.example.ajarin.domain.utils.Resource

class Register(
    private val repository: AuthRepository,
    private val tokenPreferences: TokenPreferences
) {
    suspend operator fun invoke(
        email: String,
        number: String,
        username: String,
        password: String
    ): Resource<Unit> {
        val result =  repository.register(
            email = email,
            number = number,
            username = username,
            password = password
        )

        return when (result) {
            is Resource.Error -> Resource.Error(result.message ?: "Unknown Error")
            is Resource.Loading -> Resource.Error(result.message ?: "Unknown Error")
            is Resource.Success -> {
                tokenPreferences.setToken(
                    accessToken = result.data ?: "",
                    refreshToken = result.data ?: "",
                )

                Resource.Success(Unit)
            }
        }
    }
}