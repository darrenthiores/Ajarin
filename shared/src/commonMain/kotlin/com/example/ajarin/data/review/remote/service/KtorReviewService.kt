package com.example.ajarin.data.review.remote.service

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import com.example.ajarin.data.review.remote.dto.request.CreateReviewRequest
import com.example.ajarin.data.review.remote.dto.response.ReviewResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorReviewService(
    private val client: HttpClient
): ReviewService {
    override suspend fun getMentorReviews(
        request: IdRequest,
        page: Int
    ): BaseResponse<List<ReviewResponse>> {
        val result = client.post {
            url(ReviewService.GET_MENTOR_REVIEW_URL + "/$page")
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun createReview(request: CreateReviewRequest): BaseResponse<Any?> {
        val result = client.post {
            url(ReviewService.CREATE_REVIEW_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

}