package com.example.ajarin.presentation.history

data class HistoryState(
    val historyLoading: Boolean = false,
    val historyError: Error? = null
)