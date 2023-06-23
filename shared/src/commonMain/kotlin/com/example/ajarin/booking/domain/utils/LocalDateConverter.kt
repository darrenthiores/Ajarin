package com.example.ajarin.booking.domain.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime

object LocalDateConverter {
    fun currentDate(): LocalDate {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        return LocalDate(
            year = currentDate.year,
            month = currentDate.month,
            dayOfMonth = currentDate.dayOfMonth
        )
    }

    fun toLocalDate(date: String): LocalDate {


        return date.toLocalDate()
    }

    fun localDateToEpoch(date: LocalDate): Long {
        return date
            .atStartOfDayIn(TimeZone.currentSystemDefault())
            .toEpochMilliseconds()
    }
}