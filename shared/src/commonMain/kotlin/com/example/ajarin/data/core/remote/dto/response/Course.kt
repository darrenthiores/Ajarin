package com.example.ajarin.data.core.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: String,
    val name: String
)