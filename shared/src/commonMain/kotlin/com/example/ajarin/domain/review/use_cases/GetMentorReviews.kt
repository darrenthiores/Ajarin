package com.example.ajarin.domain.review.use_cases

import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.domain.review.repository.ReviewRepository
import com.example.ajarin.domain.utils.Resource

class GetMentorReviews(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        id: String,
        page: Int
    ): List<Review> {
        val result = repository
            .getMentorReviews(
                id = id,
                page = page
            )

        return when (result) {
            is Resource.Error -> emptyList()
            is Resource.Loading -> emptyList()
            is Resource.Success -> result.data ?: emptyList()
        }
    }
}