package com.example.ajarin.history.domain.use_cases

import com.example.ajarin.history.presentation.HistorySession
import com.example.ajarin.history.presentation.dummyHistory

class GetHistory {
    fun execute(): List<HistorySession> {
        return dummyHistory
            .sortedByDescending { it.date }
    }
}