package com.example.ajarin.android.booking.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.order.use_cases.CreateOrder
import com.example.ajarin.presentation.booking.BookingEvent
import com.example.ajarin.presentation.booking.BookingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidBookingViewModel @Inject constructor(
    private val getMentorById: GetMentorById,
    private val createOrder: CreateOrder,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        BookingViewModel(
            getMentorById = getMentorById,
            createOrder = createOrder,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel.uiEvent

    fun onEvent(event: BookingEvent) {
        viewModel.onEvent(event)
    }

    init {
        val mentorId = savedStateHandle.get<String>("mentor_id")

        mentorId?.let {
            viewModel.initMentor(it)
        }
    }
}