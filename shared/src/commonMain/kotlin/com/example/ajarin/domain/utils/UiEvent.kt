package com.example.ajarin.domain.utils

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
}
