package com.example.ajarin.data.core.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class PaymentMethod(
    val id: String,
    val name: String
)