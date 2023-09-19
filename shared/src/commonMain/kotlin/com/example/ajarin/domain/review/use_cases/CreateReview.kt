package com.example.ajarin.domain.review.use_cases

import com.example.ajarin.domain.review.repository.ReviewRepository
import com.example.ajarin.domain.utils.Resource

class CreateReview(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        orderId: String,
        comment: String,
        rating: Double
    ): Resource<Unit> {
        return repository
            .createReview(
                orderId = orderId,
                comment = comment,
                rating = rating
            )
    }
}