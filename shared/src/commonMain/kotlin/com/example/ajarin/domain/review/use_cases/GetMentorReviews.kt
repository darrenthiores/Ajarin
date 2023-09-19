package com.example.ajarin.domain.review.use_cases

import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.domain.review.repository.ReviewRepository
import com.example.ajarin.domain.utils.Resource

class GetMentorReviews(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        page: Int
    ): Resource<List<Review>> {
        return repository
            .getMentorReviews(
                page = page
            )
    }
}