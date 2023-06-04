package com.example.ajarin.history.presentation

import com.example.ajarin.booking.presentation.PaymentMethod
import com.example.ajarin.booking.presentation.paymentMethods
import com.example.ajarin.home.presentation.Course
import com.example.ajarin.mentorProfile.presentation.Session
import com.example.ajarin.profile.presentation.dummyUsers
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
    val userId: String,
    val userName: String,
    val userImgUrl: String,
    val course: Course,
    val schedule: Session,
    val date: LocalDate,
    val mentorPrice: String,
    val totalPrice: String,
    val status: String,
    val paymentMethod: PaymentMethod
)

val dummyHistory = listOf(
    HistorySession(
        id = "H1",
        mentorId = "M1",
        mentorName = "Steven",
        mentorImgUrl = "https://api.time.com/wp-content/uploads/2017/12/terry-crews-person-of-year-2017-time-magazine-2.jpg?quality=85&w=1600",
        userId = dummyUsers[0].id,
        userName = dummyUsers[0].name,
        userImgUrl = "",
        course = Course(
            id = "7",
            name = "Geografi"
        ),
        schedule = Session(
            id = "2",
            time = "10:00 - 11:30"
        ),
        date = "2023-06-04".toLocalDate(),
        mentorPrice = "50.000",
        totalPrice = "50.000",
        status = "2",
        paymentMethod = paymentMethods[0]
    ),
    HistorySession(
        id = "H2",
        mentorId = "M12",
        mentorName = "Tommy",
        mentorImgUrl = "https://static.toiimg.com/photo/89456086.cms",
        userId = dummyUsers[1].id,
        userName = dummyUsers[1].name,
        userImgUrl = "",
        course = Course(
            id = "4",
            name = "Kimia"
        ),
        schedule = Session(
            id = "3",
            time = "12:00 - 13:30"
        ),
        date = "2023-06-01".toLocalDate(),
        mentorPrice = "55.000",
        totalPrice = "55.000",
        status = "3",
        paymentMethod = paymentMethods[1]
    ),
    HistorySession(
        id = "H3",
        mentorId = "M9",
        mentorName = "Yuli",
        mentorImgUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQa1pJgqNGQ7G0o2oaT3CLntytr0M2I8BlyCA&usqp=CAU",
        userId = dummyUsers[2].id,
        userName = dummyUsers[2].name,
        userImgUrl = "",
        course = Course(
            id = "10",
            name = "Sosiologi"
        ),
        schedule = Session(
            id = "7",
            time = "20:00 - 21:30"
        ),
        date = "2023-06-05".toLocalDate(),
        mentorPrice = "65.000",
        totalPrice = "65.000",
        status = "1",
        paymentMethod = paymentMethods[2]
    ),
    HistorySession(
        id = "H4",
        mentorId = "M4",
        mentorName = "Darren",
        mentorImgUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsy3zU6gHCYIgHQ1hKv1ASWQ62U_Jpe3Wdfg&usqp=CAU",
        userId = dummyUsers[3].id,
        userName = dummyUsers[3].name,
        userImgUrl = "",
        course = Course(
            id = "5",
            name = "Matematika"
        ),
        schedule = Session(
            id = "6",
            time = "18:00 - 19:30"
        ),
        date = "2023-06-06".toLocalDate(),
        mentorPrice = "100.000",
        totalPrice = "100.000",
        status = "1",
        paymentMethod = paymentMethods[3]
    )
)

fun getStatusMessage(
    status: String
): String {
    return when(status) {
        "1" -> "Wait until your schedule!"
        "2" -> "Course On Going!"
        "3" -> "Rate your experience!"
        "4" -> "Session done!"
        else -> "Unknown Status Code!"
    }
}

fun getMentorStatusMessage(
    status: String
): String {
    return when(status) {
        "1" -> "Wait until your schedule!"
        "2" -> "Course On Going!"
        "3" -> "Session done!"
        "4" -> "Session done!"
        else -> "Unknown Status Code!"
    }
}