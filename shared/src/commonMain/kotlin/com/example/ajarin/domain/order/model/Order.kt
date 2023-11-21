package com.example.ajarin.domain.order.model

import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.core.model.PaymentMethod
import com.example.ajarin.domain.core.model.paymentMethods
import com.example.ajarin.presentation.profile.dummyUsers
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

data class Order(
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
    Order(
        id = "H1",
        mentorId = "M1",
        mentorName = "Steven",
        mentorImgUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
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
        totalPrice = "55.000",
        status = "2",
        paymentMethod = paymentMethods[0]
    ),
    Order(
        id = "H2",
        mentorId = "M12",
        mentorName = "Tommy",
        mentorImgUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
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
        totalPrice = "60.000",
        status = "3",
        paymentMethod = paymentMethods[1]
    ),
    Order(
        id = "H3",
        mentorId = "M9",
        mentorName = "Yuli",
        mentorImgUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202836.png",
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
        totalPrice = "70.000",
        status = "1",
        paymentMethod = paymentMethods[2]
    ),
    Order(
        id = "H4",
        mentorId = "M4",
        mentorName = "Darren",
        mentorImgUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
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
        totalPrice = "105.000",
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