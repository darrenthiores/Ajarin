package com.example.ajarin.domain.user.use_cases

import com.example.ajarin.domain.user.model.User
import com.example.ajarin.domain.user.repository.UserRepository
import com.example.ajarin.domain.utils.Resource

class GetUserById(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        id: String
    ): Resource<User> {
        return repository.getUserById(
            id = id
        )
    }
}