package com.example.ajarin.history.presentation.utils

object StatusToMessage {
    fun getStatusMessage(
        status: String
    ): String {
        return when(status) {
            "1" -> "Wait until your schedule!"
            "2" -> "Course On Going!"
            "3" -> "Rate your experience!"
            "4" -> "Session done!"
            else -> "Unknown Status Code!"
        }
    }

    fun getMentorStatusMessage(
        status: String
    ): String {
        return when(status) {
            "1" -> "Wait until your schedule!"
            "2" -> "Course On Going!"
            "3" -> "Session done!"
            "4" -> "Session done!"
            else -> "Unknown Status Code!"
        }
    }
}