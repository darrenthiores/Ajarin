package com.example.ajarin.presentation.home

sealed class HomeEvent {
    data class OnSelectCourse(val course: Course): HomeEvent()
}
