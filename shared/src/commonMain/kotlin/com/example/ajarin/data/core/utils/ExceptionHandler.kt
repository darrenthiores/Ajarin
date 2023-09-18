package com.example.ajarin.data.core.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException


suspend fun <T> tryCatch(
    httpCall: suspend () -> ApiResponse<T>
): ApiResponse<T> =
    try {
        httpCall()
    } catch (e: RedirectResponseException) {
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: ClientRequestException) {
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: ServerResponseException) {
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: Exception) {
        ApiResponse.Error("Error: ${e.message}")
    }