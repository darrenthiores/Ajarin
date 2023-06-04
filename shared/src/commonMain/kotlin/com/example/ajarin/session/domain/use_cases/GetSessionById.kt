package com.example.ajarin.session.domain.use_cases

import com.example.ajarin.core.utils.Resource
import com.example.ajarin.history.presentation.HistorySession
import com.example.ajarin.history.presentation.dummyHistory

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