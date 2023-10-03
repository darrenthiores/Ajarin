package com.example.ajarin.data.review.repository

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.review.mapper.toReview
import com.example.ajarin.data.review.remote.ReviewRemoteDataSource
import com.example.ajarin.data.review.remote.dto.request.CreateReviewRequest
import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.domain.review.repository.ReviewRepository
import com.example.ajarin.domain.utils.Resource

class ReviewRepositoryImpl(
    private val remoteDataSource: ReviewRemoteDataSource
): ReviewRepository {

    override suspend fun getMentorReviews(
        id: String,
        page: Int
    ): Resource<List<Review>> {
        val result = remoteDataSource.getMentorReviews(
            request = IdRequest(
                id = id
            ),
            page = page
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data.map { it.toReview() }
            )
        }
    }

    override suspend fun createReview(
        orderId: String,
        comment: String,
        rating: Double
    ): Resource<Unit> {
        val result = remoteDataSource.createReview(
            request = CreateReviewRequest(
                orderId = orderId,
                comment = comment,
                rating = rating
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data
            )
        }
    }
}