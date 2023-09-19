package com.example.ajarin.data.order.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateOrderRequest(
    val id: String,
    val mainLink: String?,
    val backupLink: String?,
    val materialLink: String?,
    val status: String
)
