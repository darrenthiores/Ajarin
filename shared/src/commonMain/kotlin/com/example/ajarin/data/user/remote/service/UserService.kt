package com.example.ajarin.data.user.remote.service

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import com.example.ajarin.data.user.remote.dto.response.UserResponse

interface UserService {
    suspend fun getUser(): BaseResponse<UserResponse>
    suspend fun getUserById(request: IdRequest): BaseResponse<UserResponse>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080"
        private const val LOCAL_BASE_URL = "http://10.0.2.2:8080"
        private const val IOS_BASE_URL = "http://0.0.0.0:8080"
        const val USER_URL = "$BASE_URL/user"
    }
}