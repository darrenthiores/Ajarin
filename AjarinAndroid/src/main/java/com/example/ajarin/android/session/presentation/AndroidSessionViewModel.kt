package com.example.ajarin.android.session.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.order.use_cases.GetSessionById
import com.example.ajarin.presentation.session.SessionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidSessionViewModel @Inject constructor(
    private val getSessionById: GetSessionById,
    private val getMentorById: GetMentorById,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val viewModel by lazy {
        SessionViewModel(
            getSessionById = getSessionById,
            getMentorById = getMentorById,
            coroutineScope = viewModelScope
        )
    }
    val state = viewModel.state

    init {
        val sessionId = savedStateHandle.get<String>("session_id")
        val mentorId = savedStateHandle.get<String>("mentor_id")

        viewModel.init(
            sessionId = sessionId ?: "",
            mentorId = mentorId ?: ""
        )
    }
}