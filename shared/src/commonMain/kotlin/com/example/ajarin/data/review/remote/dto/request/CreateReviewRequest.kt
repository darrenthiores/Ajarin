package com.example.ajarin.data.review.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateReviewRequest(
    val orderId: String,
    val comment: String,
    val rating: Double
)
