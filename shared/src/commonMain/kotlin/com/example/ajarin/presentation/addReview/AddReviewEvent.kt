package com.example.ajarin.presentation.addReview

sealed class AddReviewEvent {
    data class OnReviewChange(val newText: String): AddReviewEvent()
    data class OnPickRating(val rating: Int): AddReviewEvent()
    object OnPostReview: AddReviewEvent()
}
