package com.example.ajarin.data.review.remote

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.core.utils.tryCatch
import com.example.ajarin.data.review.remote.dto.request.CreateReviewRequest
import com.example.ajarin.data.review.remote.dto.response.ReviewResponse
import com.example.ajarin.data.review.remote.service.ReviewService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class ReviewRemoteDataSource(
    private val apiService: ReviewService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getMentorReviews(
        request: IdRequest,
        page: Int
    ): ApiResponse<List<ReviewResponse>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMentorReviews(
                    request = request,
                    page = page
                )

                if (result.meta.success) {
                    result.data?.let {
                        return@tryCatch ApiResponse.Success(it)
                    }

                    ApiResponse.Error(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun createReview(
        request: CreateReviewRequest
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.createReview(
                    request = request
                )

                if (result.meta.success) {
                    ApiResponse.Success(Unit)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}