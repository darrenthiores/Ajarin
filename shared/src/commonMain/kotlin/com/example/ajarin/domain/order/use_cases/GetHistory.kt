package com.example.ajarin.domain.order.use_cases

import com.example.ajarin.presentation.history.HistorySession
import com.example.ajarin.presentation.history.dummyHistory

class GetHistory {
    fun execute(): List<HistorySession> {
        return dummyHistory
            .sortedByDescending { it.date }
    }
}