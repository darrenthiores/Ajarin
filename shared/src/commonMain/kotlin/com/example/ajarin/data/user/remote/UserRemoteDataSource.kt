package com.example.ajarin.data.user.remote

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.core.utils.tryCatch
import com.example.ajarin.data.user.remote.dto.response.UserResponse
import com.example.ajarin.data.user.remote.service.UserService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class UserRemoteDataSource(
    private val apiService: UserService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getUser(): ApiResponse<UserResponse> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUser()

                if (result.meta.success) {
                    result.data?.let {
                        ApiResponse.Success(it)
                    }

                    ApiResponse.Error(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun getUserById(
        request: IdRequest
    ): ApiResponse<UserResponse> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUserById(
                    request = request
                )

                if (result.meta.success) {
                    result.data?.let {
                        return@tryCatch ApiResponse.Success(it)
                    }

                    ApiResponse.Error(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}