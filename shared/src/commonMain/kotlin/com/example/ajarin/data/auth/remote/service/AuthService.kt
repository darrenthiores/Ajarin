package com.example.ajarin.data.auth.remote.service

import com.example.ajarin.data.auth.remote.dto.request.LoginRequest
import com.example.ajarin.data.auth.remote.dto.request.RegisterRequest
import com.example.ajarin.data.auth.remote.dto.response.AuthResponse
import com.example.ajarin.data.core.remote.dto.response.BaseResponse

interface AuthService {
    suspend fun login(request: LoginRequest): BaseResponse<AuthResponse?>
    suspend fun register(request: RegisterRequest): BaseResponse<AuthResponse?>
    suspend fun resetToken()

    companion object {
        private const val BASE_URL = "https://ajarin-400903.et.r.appspot.com"
        private const val LOCAL_BASE_URL = "http://10.0.2.2:8080"
        private const val IOS_BASE_URL = "http://0.0.0.0:8080"
        const val LOGIN_URL = "$BASE_URL/user/login"
        const val REGISTER_URL = "$BASE_URL/user/register"
    }
}