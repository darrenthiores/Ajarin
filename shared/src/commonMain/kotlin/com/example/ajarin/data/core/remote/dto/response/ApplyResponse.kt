package com.example.ajarin.data.core.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ApplyResponse(
    val meta: Meta,
    val data: String?
) {
    @Serializable
    data class Meta(
        val success: Boolean,
        val message: String
    )
}
