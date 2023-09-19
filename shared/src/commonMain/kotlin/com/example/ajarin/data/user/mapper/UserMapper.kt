package com.example.ajarin.data.user.mapper

import com.example.ajarin.data.user.remote.dto.response.UserResponse
import com.example.ajarin.domain.user.model.User

fun UserResponse.toUser(): User {
    return User(
        id = id,
        name = username,
        email = email,
        number = number,
        roleType = roleId.toString()
    )
}