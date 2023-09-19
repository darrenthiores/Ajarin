package com.example.ajarin.domain.user.repository

import com.example.ajarin.domain.user.model.User
import com.example.ajarin.domain.utils.Resource

interface UserRepository {
    suspend fun getUser(): Resource<User>

    suspend fun getUserById(
        id: String
    ): Resource<User>
}