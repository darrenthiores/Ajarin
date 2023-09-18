package com.example.ajarin.domain.core.preferences

interface TokenPreferences {
    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun setToken(
        accessToken: String,
        refreshToken: String
    )

    fun resetToken()
}