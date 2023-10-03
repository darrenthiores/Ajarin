package com.example.ajarin.data.auth.repository

import com.example.ajarin.data.auth.remote.AuthRemoteDataSource
import com.example.ajarin.data.auth.remote.dto.request.LoginRequest
import com.example.ajarin.data.auth.remote.dto.request.RegisterRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.domain.auth.repository.AuthRepository
import com.example.ajarin.domain.utils.Resource

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
): AuthRepository {
    override suspend fun login(email: String, password: String): Resource<String> {
        val result = remoteDataSource.login(
            request = LoginRequest(
                email = email,
                password = password
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data.token)
        }
    }

    override suspend fun register(
        email: String,
        number: String,
        username: String,
        password: String
    ): Resource<String> {
        val result = remoteDataSource.register(
            request = RegisterRequest(
                email = email,
                username = username,
                password = password,
                number = number
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data.token)
        }
    }

    override suspend fun resetToken() {
        remoteDataSource.resetToken()
    }
}