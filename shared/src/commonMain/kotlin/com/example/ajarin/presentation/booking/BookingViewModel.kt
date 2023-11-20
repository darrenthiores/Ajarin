package com.example.ajarin.presentation.booking

import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.order.use_cases.CreateOrder
import com.example.ajarin.domain.utils.LocalDateConverter
import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.domain.utils.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BookingViewModel(
    private val getMentorById: GetMentorById,
    private val createOrder: CreateOrder,
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

                viewModelScope.launch {
                    val stateValue = state.value
                    val result = createOrder(
                        mentorId = stateValue.mentor?.id ?: return@launch,
                        courseId = stateValue.course?.id ?: return@launch,
                        date = LocalDateConverter.localDateToEpoch(stateValue.date ?: return@launch),
                        sessionId = stateValue.schedule?.id ?: return@launch,
                        paymentMethodId = stateValue.paymentMethod?.id ?: return@launch
                    )

                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isError = Error(result.message)
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                bookingSuccess = true
                            )

                            viewModelScope.launch {
                                _uiEvent.send(
                                    UiEvent.Success
                                )
                            }
                        }
                    }
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
        viewModelScope.launch {
            _state.value = state.value.copy(
                isFetching = true
            )

            when(
                val result = getMentorById(id)
            ) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isError = Error(result.message),
                        isFetching = false
                    )
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    val mentorPrice = result.data?.price?.toIntOrNull() ?: 0
                    val fee = mentorPrice * 0.1
                    val fixedFee = if (fee == 0.0) "5000" else fee.toString()

                    _state.value = state.value.copy(
                        isError = null,
                        isFetching = false,
                        mentor = result.data,
                        platformFee = fixedFee
                    )
                }
            }
        }
    }
}