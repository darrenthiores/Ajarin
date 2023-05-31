package com.example.ajarin.mentorProfile.presentation

import com.example.ajarin.home.presentation.Mentor

data class MentorProfileState(
    val mentor: Mentor? = null,
    val isFetching: Boolean = false,
    val isError: Error? = null,
    val reviews: List<Review> = dummyReviews
)

data class Session(
    val id: String,
    val time: String // format hh:MM
) {
    companion object {
        fun currentSession(hour: Int): String {
            return when (hour) {
                in 8..9 -> "1"
                in 10..11 -> "2"
                in 12..13 -> "3"
                in 14..15 -> "4"
                in 16..17 -> "5"
                in 18..19 -> "6"
                in 20..21 -> "7"
                else -> "0"
            }
        }
    }
}

val sessions = listOf(
    Session(
        id = "1",
        time = "08:00 - 09:30"
    ),
    Session(
        id = "2",
        time = "10:00 - 11:30"
    ),
    Session(
        id = "3",
        time = "12:00 - 13:30"
    ),
    Session(
        id = "4",
        time = "14:00 - 15:30"
    ),
    Session(
        id = "5",
        time = "16:00 - 17:30"
    ),
    Session(
        id = "6",
        time = "18:00 - 19:30"
    ),
    Session(
        id = "7",
        time = "20:00 - 21:30"
    )
)

data class Review(
    val reviewId: String,
    val userId: String,
    val mentorId: String,
    val username: String,
    val userPhotoUrl: String,
    val rating: String,
    val comment: String,
    val imagesUrl: List<String>,
    val reviewDate: String,
    val sessionId: String
)

val dummyReviews = listOf(
    Review(
        reviewId = "R1",
        userId = "U1",
        mentorId = "M1",
        username = "darren thiores",
        userPhotoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbmB8QjPQMaiVi3yB0IckvPI1yiaYQLaAQ4g&usqp=CAU",
        rating = "4.6",
        comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        imagesUrl = listOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbmB8QjPQMaiVi3yB0IckvPI1yiaYQLaAQ4g&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbmB8QjPQMaiVi3yB0IckvPI1yiaYQLaAQ4g&usqp=CAU"
        ),
        reviewDate = "31-05-2023",
        sessionId = "1"
    ),
    Review(
        reviewId = "R2",
        userId = "U2",
        mentorId = "M5",
        username = "kevin valentino",
        userPhotoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2nUnKJ6sV_R2GAzbFjIRWxUsUgvecJwmXDw&usqp=CAU",
        rating = "4.0",
        comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        imagesUrl = listOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2nUnKJ6sV_R2GAzbFjIRWxUsUgvecJwmXDw&usqp=CAU"
        ),
        reviewDate = "07-06-2023",
        sessionId = "5"
    )
)