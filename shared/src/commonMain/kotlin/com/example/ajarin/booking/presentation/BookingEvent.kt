package com.example.ajarin.booking.presentation

import com.example.ajarin.home.presentation.Course
import com.example.ajarin.mentorProfile.presentation.Session
import kotlinx.datetime.LocalDate

sealed class BookingEvent {
    data class PickCourse(val course: Course): BookingEvent()
    data class PickSchedule(val session: Session): BookingEvent()
    data class PickDate(val date: LocalDate): BookingEvent()
    data class PickPaymentMethod(val method: PaymentMethod): BookingEvent()
    data class ToggleCourseDropDown(val isOpen: Boolean): BookingEvent()
    object Book: BookingEvent()
}
