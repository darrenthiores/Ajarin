package com.example.ajarin.presentation.addReview

import com.example.ajarin.domain.order.model.Order

data class AddReviewState(
    val historySession: Order? = null,
    val isSessionLoading: Boolean = false,
    val isSessionError: Error? = null,
    val reviewText: String = "",
    val rating: Int = 0,
    val isPosting: Boolean = false,
    val isPostSuccess: Boolean = false,
    val isPostError: Error? = null
)
