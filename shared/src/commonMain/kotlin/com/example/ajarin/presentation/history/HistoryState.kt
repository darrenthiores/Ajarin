package com.example.ajarin.presentation.history

data class HistoryState(
    val isMentor: Boolean = false,
    val historyLoading: Boolean = false,
    val historyError: Error? = null
)