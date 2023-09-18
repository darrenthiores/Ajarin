package com.example.ajarin.presentation.booking

import com.example.ajarin.domain.utils.LocalDateConverter
import com.example.ajarin.presentation.home.Course
import com.example.ajarin.presentation.home.Mentor
import com.example.ajarin.presentation.mentorProfile.Session
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
    val bookingSuccess: Boolean = false
)

data class PaymentMethod(
    val id: String,
    val name: String
)

val paymentMethods = listOf(
    PaymentMethod(
        id = "0",
        name = "Dana"
    ),
    PaymentMethod(
        id = "1",
        name = "Gopay"
    ),
    PaymentMethod(
        id = "2",
        name = "Shopee Pay"
    ),
    PaymentMethod(
        id = "3",
        name = "Ovo"
    )
)
