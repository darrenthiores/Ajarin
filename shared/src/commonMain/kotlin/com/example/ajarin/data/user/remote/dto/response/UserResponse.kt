package com.example.ajarin.data.user.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val username: String,
    val roleId: Int,
    val email: String,
    val number: String,
    val createdAt: Long,
    val modifiedAt: Long
)
