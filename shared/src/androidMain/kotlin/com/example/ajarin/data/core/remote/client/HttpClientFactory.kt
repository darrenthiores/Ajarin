package com.example.ajarin.data.core.remote.client

import com.example.ajarin.domain.core.preferences.TokenPreferences
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json

actual class HttpClientFactory {
    actual fun create(
        tokenPreferences: TokenPreferences
    ): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.HEADERS
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = tokenPreferences.getAccessToken(),
                            refreshToken = tokenPreferences.getRefreshToken()
                        )
                    }
//                    refreshTokens {
//                        val client = this.client
//                        val refreshUrl = "token?client_id=dofavourMobileApp&client_secret=4bc6ca919677181eabd430ea7f9ed6ffcf4c65e2e8cd56f8072c390f0a3fc9c7&refresh_token=${tokenPreferences.getRefreshToken()}&scope=read:user,read:email&grant_type=refresh_token"
//
//                        try {
//                            val response = client.get {
//                                url(refreshUrl)
//                                contentType(ContentType.Application.Json)
//
//                                headers {
//                                    append(HttpHeaders.Authorization, "Bearer ${tokenPreferences.getAccessToken()}")
//                                }
//                            }
//
//                            if(response!=null) {
//                                if(!response.success) {
//                                    return@refreshTokens BearerTokens(
//                                        accessToken = "",
//                                        refreshToken = ""
//                                    )
//                                } else {
//                                    encPreference.setToken(
//                                        response.data.access_token,
//                                        response.data.refresh_token
//                                    )
//
//                                    return@refreshTokens BearerTokens(
//                                        response.data.access_token,
//                                        response.data.refresh_token
//                                    )
//                                }
//                            } else {
//                                return@refreshTokens BearerTokens(
//                                    accessToken = "",
//                                    refreshToken = ""
//                                )
//                            }
//                        } catch (e: Exception) {
//                            return@refreshTokens BearerTokens(
//                                accessToken = "",
//                                refreshToken = ""
//                            )
//                        }
//                    }
                    sendWithoutRequest {
                        !it.url.encodedPath.startsWith("/local/login") &&
                                !it.url.encodedPath.startsWith("/local/register")
                    }
                }
            }
        }
    }
}