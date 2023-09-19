package com.example.ajarin.presentation.searchMentor

import com.example.ajarin.domain.core.model.Course

sealed class SearchMentorEvent {
    data class OnSearchChange(val newText: String): SearchMentorEvent()
    object OnToggleRating: SearchMentorEvent()
    data class OnPickCourse(val course: Course): SearchMentorEvent()
    data class OnPickPrice(val price: String): SearchMentorEvent()
    data class OnPickEducation(val education: String): SearchMentorEvent()
    object OnReset: SearchMentorEvent()
    object OnApply: SearchMentorEvent()
}