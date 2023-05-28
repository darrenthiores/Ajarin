package com.example.ajarin.home.presentation

sealed class HomeEvent {
    data class OnSelectCourse(val course: Course): HomeEvent()
}
