package com.example.ajarin.data.user.remote.service

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import com.example.ajarin.data.user.remote.dto.response.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorUserService(
    private val client: HttpClient
): UserService {
    override suspend fun getUser(): BaseResponse<UserResponse> {
        val result = client.get {
            url(UserService.USER_URL)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun getUserById(request: IdRequest): BaseResponse<UserResponse> {
        val result = client.post {
            url(UserService.USER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

}