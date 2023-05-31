package com.example.ajarin.booking.presentation

import com.example.ajarin.core.domain.utils.toCommonFlow
import com.example.ajarin.core.domain.utils.toCommonStateFlow
import com.example.ajarin.core.utils.Resource
import com.example.ajarin.core.utils.UiEvent
import com.example.ajarin.mentorProfile.domain.use_cases.GetMentorById
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BookingViewModel(
    private val getMentorById: GetMentorById,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(BookingState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: BookingEvent) {
        when (event) {
            BookingEvent.Book -> {
                if (state.value.course == null) {
                    _state.value = state.value.copy(
                        courseError = Error("Please pick any available course")
                    )

                    return
                }

                if (state.value.schedule == null) {
                    _state.value = state.value.copy(
                        scheduleError = Error("Please pick any available schedule")
                    )

                    return
                }

                if (state.value.paymentMethod == null) {
                    _state.value = state.value.copy(
                        paymentMethodError = Error("Please pick payment method")
                    )

                    return
                }

                _state.value = state.value.copy(
                    bookingSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            is BookingEvent.PickCourse -> {
                _state.value = state.value.copy(
                    course = event.course,
                    courseError = null
                )
            }
            is BookingEvent.PickPaymentMethod -> {
                _state.value = state.value.copy(
                    paymentMethod = event.method,
                    paymentMethodError = null
                )
            }
            is BookingEvent.PickSchedule -> {
                _state.value = state.value.copy(
                    schedule = event.session,
                    scheduleError = null
                )
            }
            is BookingEvent.PickDate -> {
                _state.value = state.value.copy(
                    date = event.date,
                    dateError = null
                )
            }
            is BookingEvent.ToggleCourseDropDown -> {
                _state.value = state.value.copy(
                    isCourseDropDownOpen = event.isOpen
                )
            }
        }
    }

    fun initMentor(id: String) {
        _state.value = state.value.copy(
            isFetching = true
        )

        when(
            val result = getMentorById.execute(id)
        ) {
            is Resource.Error -> {
                _state.value = state.value.copy(
                    isError = Error(result.message),
                    isFetching = false
                )
            }
            is Resource.Loading -> Unit
            is Resource.Success -> {
                _state.value = state.value.copy(
                    isError = null,
                    isFetching = false,
                    mentor = result.data
                )
            }
        }
    }
}