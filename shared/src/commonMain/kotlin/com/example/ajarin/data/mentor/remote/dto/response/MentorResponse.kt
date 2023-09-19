package com.example.ajarin.data.mentor.remote.dto.response

import com.example.ajarin.data.core.remote.dto.response.Course
import kotlinx.serialization.Serializable

@Serializable
data class MentorResponse(
    val id: String,
    val photoUrl: String,
    val name: String,
    val education: String,
    val rating: Double,
    val courses: List<Course>,
    val price: String,
    val priceCategory: String,
    val createdAt: Long,
    val modifiedAt: Long
)
