package com.example.ajarin.domain.order.use_cases

import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.presentation.history.HistorySession
import com.example.ajarin.presentation.history.dummyHistory

class GetSessionById {
    fun execute(
        id: String
    ): Resource<HistorySession> {
        dummyHistory
            .firstOrNull { it.id == id }
            ?.let { session ->
                return Resource.Success(
                    data = session
                )
            } ?: return Resource.Error(
                message = "There is no session with such id"
            )
    }
}