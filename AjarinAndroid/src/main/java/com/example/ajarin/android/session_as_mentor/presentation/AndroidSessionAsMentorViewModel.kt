package com.example.ajarin.android.session_as_mentor.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.domain.order.use_cases.GetOrderById
import com.example.ajarin.domain.order.use_cases.UpdateOrder
import com.example.ajarin.domain.user.use_cases.GetUserById
import com.example.ajarin.presentation.sessionAsMentor.SessionAsMentorEvent
import com.example.ajarin.presentation.sessionAsMentor.SessionAsMentorViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidSessionAsMentorViewModel @Inject constructor(
    private val getOrderById: GetOrderById,
    private val getUserById: GetUserById,
    private val updateOrder: UpdateOrder,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val viewModel by lazy {
        SessionAsMentorViewModel(
            getOrderById = getOrderById,
            getUserById = getUserById,
            updateOrder = updateOrder,
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