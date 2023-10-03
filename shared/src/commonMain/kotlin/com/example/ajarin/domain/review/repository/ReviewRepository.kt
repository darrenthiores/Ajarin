package com.example.ajarin.domain.review.repository

import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.domain.utils.Resource

interface ReviewRepository {
    suspend fun getMentorReviews(
        id: String,
        page: Int
    ): Resource<List<Review>>
    suspend fun createReview(
        orderId: String,
        comment: String,
        rating: Double
    ): Resource<Unit>
}