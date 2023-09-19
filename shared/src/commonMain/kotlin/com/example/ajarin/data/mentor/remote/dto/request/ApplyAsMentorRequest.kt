package com.example.ajarin.data.mentor.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class ApplyAsMentorRequest(
    val id: String,
    val education: String,
    val courses: List<String>,
    val price: String
)
