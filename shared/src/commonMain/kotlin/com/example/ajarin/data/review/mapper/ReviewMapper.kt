package com.example.ajarin.data.review.mapper

import com.example.ajarin.data.review.remote.dto.response.ReviewResponse
import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.domain.utils.LocalDateConverter

fun ReviewResponse.toReview(): Review {
    return Review(
        reviewId = reviewId,
        userId = userId,
        mentorId = mentorId,
        username = username,
        userPhotoUrl = userPhotoUrl,
        rating = rating,
        comment = comment,
        imagesUrl = imagesUrl,
        reviewDate = LocalDateConverter
            .timestampToLocalDate(reviewDate)
            .toString(),
        sessionId = sessionId
    )
}