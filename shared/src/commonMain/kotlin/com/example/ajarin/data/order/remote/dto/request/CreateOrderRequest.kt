package com.example.ajarin.data.order.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequest(
    val mentorId: String,
    val courseId: String,
    val date: Long,
    val sessionId: String,
    val paymentMethodId: String
)
