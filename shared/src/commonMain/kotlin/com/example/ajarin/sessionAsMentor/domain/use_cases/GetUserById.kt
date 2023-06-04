package com.example.ajarin.sessionAsMentor.domain.use_cases

import com.example.ajarin.core.utils.Resource
import com.example.ajarin.profile.presentation.User
import com.example.ajarin.profile.presentation.dummyUsers

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