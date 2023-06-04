package com.example.ajarin.android.session_as_mentor.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.session.domain.use_cases.GetSessionById
import com.example.ajarin.sessionAsMentor.domain.use_cases.GetUserById
import com.example.ajarin.sessionAsMentor.presentation.SessionAsMentorEvent
import com.example.ajarin.sessionAsMentor.presentation.SessionAsMentorViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidSessionAsMentorViewModel @Inject constructor(
    private val getSessionById: GetSessionById,
    private val getUserById: GetUserById,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val viewModel by lazy {
        SessionAsMentorViewModel(
            getSessionById = getSessionById,
            getUserById = getUserById,
            coroutineScope = viewModelScope
        )
    }
    val state = viewModel.state

    fun onEvent(event: SessionAsMentorEvent) {
        viewModel.onEvent(event)
    }

    init {
        val sessionId = savedStateHandle.get<String>("session_id")
        val userId = savedStateHandle.get<String>("user_id")

        viewModel.init(
            sessionId = sessionId ?: "",
            userId = userId ?: ""
        )
    }
}