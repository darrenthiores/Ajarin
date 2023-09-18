package com.example.ajarin.data.core.remote.client

import com.example.ajarin.domain.core.preferences.TokenPreferences
import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(tokenPreferences: TokenPreferences): HttpClient
}