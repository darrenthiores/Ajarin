package com.example.ajarin.data.auth.remote

import com.example.ajarin.data.auth.remote.dto.request.LoginRequest
import com.example.ajarin.data.auth.remote.dto.request.RegisterRequest
import com.example.ajarin.data.auth.remote.dto.response.AuthResponse
import com.example.ajarin.data.auth.remote.service.AuthService
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.core.utils.tryCatch
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class AuthRemoteDataSource(
    private val apiService: AuthService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun login(
        request: LoginRequest
    ): ApiResponse<AuthResponse> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.login(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun register(
        request: RegisterRequest
    ): ApiResponse<AuthResponse> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.register(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}