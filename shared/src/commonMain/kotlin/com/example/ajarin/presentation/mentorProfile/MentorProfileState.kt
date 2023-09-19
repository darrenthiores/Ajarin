package com.example.ajarin.presentation.mentorProfile

import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.domain.review.model.dummyReviews

data class MentorProfileState(
    val mentor: Mentor? = null,
    val isFetching: Boolean = false,
    val isError: Error? = null,
    val reviews: List<Review> = dummyReviews
)