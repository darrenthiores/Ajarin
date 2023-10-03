package com.example.ajarin.domain.user.use_cases

import com.example.ajarin.domain.user.model.User
import com.example.ajarin.domain.user.repository.UserRepository
import com.example.ajarin.domain.utils.Resource

class GetUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): User? {
        val result = repository.getUser()

        return when (result) {
            is Resource.Error -> null
            is Resource.Loading -> null
            is Resource.Success -> result.data
        }
    }
}