package com.example.ajarin.data.auth.remote.service

import com.example.ajarin.data.auth.remote.dto.request.LoginRequest
import com.example.ajarin.data.auth.remote.dto.request.RegisterRequest
import com.example.ajarin.data.auth.remote.dto.response.AuthResponse
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorAuthService(
    private val client: HttpClient
): AuthService {
    override suspend fun login(request: LoginRequest): BaseResponse<AuthResponse> {
        val result = client.post {
            url(AuthService.LOGIN_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun register(request: RegisterRequest): BaseResponse<AuthResponse> {
        val result = client.post {
            url(AuthService.REGISTER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }
}