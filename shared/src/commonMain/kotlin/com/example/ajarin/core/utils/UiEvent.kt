package com.example.ajarin.core.utils

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
}
