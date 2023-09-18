package com.example.ajarin.domain.auth.use_cases

import com.example.ajarin.domain.auth.repository.AuthRepository
import com.example.ajarin.domain.core.preferences.TokenPreferences
import com.example.ajarin.domain.utils.Resource

class Login(
    private val repository: AuthRepository,
    private val tokenPreferences: TokenPreferences
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Resource<Unit> {
        val result =  repository.login(
            email = email,
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