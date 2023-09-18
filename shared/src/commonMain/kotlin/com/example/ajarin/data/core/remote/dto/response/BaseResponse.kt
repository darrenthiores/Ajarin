package com.example.ajarin.data.core.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse <T> (
    val meta: Meta,
    val data: T
) {
    @Serializable
    data class Meta(
        val success: Boolean,
        val message: String
    )
}
