package com.example.ajarin.history.presentation

import com.example.ajarin.home.presentation.Course
import com.example.ajarin.mentorProfile.presentation.Session
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

data class HistoryState(
    val historySessions: List<HistorySession> = emptyList(),
    val historyLoading: Boolean = false,
    val historyError: Error? = null
)

data class HistorySession(
    val id: String,
    val mentorId: String,
    val mentorName: String,
    val mentorImgUrl: String,
    val course: Course,
    val schedule: Session,
    val date: LocalDate,
    val totalPrice: String,
    val status: String
)

val dummyHistory = listOf(
    HistorySession(
        id = "H1",
        mentorId = "M1",
        mentorName = "Steven",
        mentorImgUrl = "https://api.time.com/wp-content/uploads/2017/12/terry-crews-person-of-year-2017-time-magazine-2.jpg?quality=85&w=1600",
        course = Course(
            id = "7",
            name = "Geografi"
        ),
        schedule = Session(
            id = "2",
            time = "10:00 - 11:30"
        ),
        date = "2023-06-04".toLocalDate(),
        totalPrice = "50.000",
        status = "2"
    ),
    HistorySession(
        id = "H2",
        mentorId = "M12",
        mentorName = "Tommy",
        mentorImgUrl = "https://static.toiimg.com/photo/89456086.cms",
        course = Course(
            id = "4",
            name = "Kimia"
        ),
        schedule = Session(
            id = "3",
            time = "12:00 - 13:30"
        ),
        date = "2023-06-01".toLocalDate(),
        totalPrice = "55.000",
        status = "3"
    ),
    HistorySession(
        id = "H3",
        mentorId = "M9",
        mentorName = "Yuli",
        mentorImgUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQa1pJgqNGQ7G0o2oaT3CLntytr0M2I8BlyCA&usqp=CAU",
        course = Course(
            id = "10",
            name = "Sosiologi"
        ),
        schedule = Session(
            id = "7",
            time = "20:00 - 21:30"
        ),
        date = "2023-06-05".toLocalDate(),
        totalPrice = "65.000",
        status = "1"
    ),
    HistorySession(
        id = "H4",
        mentorId = "M4",
        mentorName = "Darren",
        mentorImgUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsy3zU6gHCYIgHQ1hKv1ASWQ62U_Jpe3Wdfg&usqp=CAU",
        course = Course(
            id = "5",
            name = "Matematika"
        ),
        schedule = Session(
            id = "6",
            time = "18:00 - 19:30"
        ),
        date = "2023-06-06".toLocalDate(),
        totalPrice = "100.000",
        status = "1"
    )
)

fun getStatusMessage(
    status: String
): String {
    return when(status) {
        "1" -> "Wait until your schedule!"
        "2" -> "Course OnGoing!"
        "3" -> "Session done!"
        else -> "Unknown Status Code!"
    }
}