package com.example.ajarin.presentation.home

import com.example.ajarin.domain.core.model.Course

sealed class HomeEvent {
    data class OnSelectCourse(val course: Course): HomeEvent()
}
