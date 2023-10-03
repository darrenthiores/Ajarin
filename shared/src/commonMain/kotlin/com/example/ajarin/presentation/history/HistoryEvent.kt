package com.example.ajarin.presentation.history

sealed class HistoryEvent {
    data class OnUpdateIsMentor(val isMentor: Boolean): HistoryEvent()
}