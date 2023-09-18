package com.example.ajarin.domain.user.use_cases

import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.presentation.profile.User
import com.example.ajarin.presentation.profile.dummyUsers

class GetUserById {
    fun execute(
        id: String
    ): Resource<User> {
        dummyUsers
            .firstOrNull { it.id == id }
            ?.let { user ->
                return Resource.Success(
                    data = user
                )
            } ?: return Resource.Error(
                message = "There is no user with such id"
            )
    }
}