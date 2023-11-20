package com.example.ajarin.presentation.booking

import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.core.model.PaymentMethod
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.order.model.Session
import com.example.ajarin.domain.utils.LocalDateConverter
import kotlinx.datetime.LocalDate

data class BookingState(
    val mentor: Mentor? = null,
    val isFetching: Boolean = false,
    val isError: Error? = null,
    val isCourseDropDownOpen: Boolean = false,
    val course: Course? = null,
    val courseError: Error? = null,
    val date: LocalDate? = LocalDateConverter.currentDate(),
    val dateError: Error? = null,
    val schedule: Session? = null,
    val scheduleError: Error? = null,
    val paymentMethod: PaymentMethod? = null,
    val paymentMethodError: Error? = null,
    val platformFee: String? = null,
    val bookingSuccess: Boolean = false
)
