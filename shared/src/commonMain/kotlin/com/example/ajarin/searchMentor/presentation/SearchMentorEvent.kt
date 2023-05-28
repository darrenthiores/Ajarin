package com.example.ajarin.searchMentor.presentation

import com.example.ajarin.home.presentation.Course

sealed class SearchMentorEvent {
    data class OnSearchChange(val newText: String): SearchMentorEvent()
    object OnSearch: SearchMentorEvent()
    object OnToggleRating: SearchMentorEvent()
    data class OnPickCourse(val course: Course): SearchMentorEvent()
    data class OnPickPrice(val price: String): SearchMentorEvent()
    data class OnPickEducation(val education: String): SearchMentorEvent()
    object OnReset: SearchMentorEvent()
    object OnApply: SearchMentorEvent()
}