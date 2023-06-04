package com.example.ajarin.addReview.presentation

import com.example.ajarin.history.presentation.HistorySession

data class AddReviewState(
    val historySession: HistorySession? = null,
    val isSessionLoading: Boolean = false,
    val isSessionError: Error? = null,
    val reviewText: String = "",
    val rating: Int = 0,
    val isPosting: Boolean = false,
    val isPostSuccess: Boolean = false,
    val isPostError: Error? = null
)
