package com.example.ajarin.data.review.remote.service

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import com.example.ajarin.data.review.remote.dto.request.CreateReviewRequest
import com.example.ajarin.data.review.remote.dto.response.ReviewResponse

interface ReviewService {
    suspend fun getMentorReviews(
        request: IdRequest,
        page: Int
    ): BaseResponse<List<ReviewResponse>>
    suspend fun createReview(
        request: CreateReviewRequest
    ): BaseResponse<Any?>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080"
        private const val LOCAL_BASE_URL = "http://10.0.2.2:8080"
        private const val IOS_BASE_URL = "http://0.0.0.0t:8080"
        const val REVIEW_URL = "$IOS_BASE_URL/review"
        const val GET_MENTOR_REVIEW_URL = "$IOS_BASE_URL/review/mentor"
        const val CREATE_REVIEW_URL = "$IOS_BASE_URL/review/create"
    }
}