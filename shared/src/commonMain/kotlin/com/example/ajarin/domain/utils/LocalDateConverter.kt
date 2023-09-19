package com.example.ajarin.domain.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
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

    fun timestampToLocalDate(
        timestamp: Long
    ): LocalDate {
        return Instant
            .fromEpochMilliseconds(timestamp)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
    }

    fun toLocalDate(date: String): LocalDate {
        return date.toLocalDate()
    }

    fun localDateToEpoch(date: LocalDate): Long {
        return date
            .atStartOfDayIn(TimeZone.currentSystemDefault())
            .toEpochMilliseconds()
    }

    fun localDateToString(date: LocalDate): String {
        val year = date.year
        val month = if(date.monthNumber < 9) "0${date.monthNumber}" else date.monthNumber
        val day = if(date.dayOfMonth < 9) "0${date.dayOfMonth}" else date.dayOfMonth

        return "$year-$month-$day"
    }
}