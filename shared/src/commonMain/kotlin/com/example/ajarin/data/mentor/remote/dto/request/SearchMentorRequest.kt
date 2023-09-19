package com.example.ajarin.data.mentor.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class SearchMentorRequest(
    val name: String,
    val education: String,
    val rating: Double,
    val courseId: String,
    val price: String
)
