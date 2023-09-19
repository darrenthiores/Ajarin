package com.example.ajarin.data.user.repository

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.user.mapper.toUser
import com.example.ajarin.data.user.remote.UserRemoteDataSource
import com.example.ajarin.domain.user.model.User
import com.example.ajarin.domain.user.repository.UserRepository
import com.example.ajarin.domain.utils.Resource

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun getUser(): Resource<User> {
        val result = remoteDataSource.getUser()

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data.toUser())
        }
    }

    override suspend fun getUserById(id: String): Resource<User> {
        val result = remoteDataSource.getUserById(
            request = IdRequest(
                id = id
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data.toUser())
        }
    }
}