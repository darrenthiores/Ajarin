package com.example.ajarin.searchMentor.presentation

import com.example.ajarin.core.domain.utils.toCommonStateFlow
import com.example.ajarin.home.presentation.dummyMentors
import com.example.ajarin.searchMentor.domain.use_cases.SearchMentor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

class SearchMentorViewModel(
    private val searchMentor: SearchMentor,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SearchMentorState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: SearchMentorEvent) {
        when(event) {
            SearchMentorEvent.OnApply -> {
                _state.value = state.value.copy(
                    selectedCourse = state.value.tempCourse,
                    rating = state.value.tempRating,
                    education = state.value.tempEducation,
                    price = state.value.tempPrice
                )

                onSearch()
            }
            is SearchMentorEvent.OnPickCourse -> {
                if (state.value.tempCourse == event.course) {
                    _state.value = state.value.copy(
                        tempCourse = null
                    )
                } else {
                    _state.value = state.value.copy(
                        tempCourse = event.course
                    )
                }
            }
            is SearchMentorEvent.OnPickEducation -> {
                if (state.value.tempEducation == event.education) {
                    _state.value = state.value.copy(
                        tempEducation = ""
                    )
                } else {
                    _state.value = state.value.copy(
                        tempEducation = event.education
                    )
                }
            }
            is SearchMentorEvent.OnPickPrice -> {
                if (state.value.tempPrice == event.price) {
                    _state.value = state.value.copy(
                        tempPrice = ""
                    )
                } else {
                    _state.value = state.value.copy(
                        tempPrice = event.price
                    )
                }
            }
            SearchMentorEvent.OnReset -> {
                _state.value = state.value.copy(
                    tempCourse = null,
                    tempRating = 0,
                    tempEducation = "",
                    tempPrice = ""
                )
            }
            SearchMentorEvent.OnSearch -> {
                onSearch()
            }
            SearchMentorEvent.OnToggleRating -> {
                if (state.value.tempRating != 0) {
                    _state.value = state.value.copy(
                        tempRating = 0
                    )
                } else {
                    _state.value = state.value.copy(
                        tempRating = 4
                    )
                }
            }
            is SearchMentorEvent.OnSearchChange -> {
                _state.value = state.value.copy(
                    searchText = event.newText
                )
            }
        }
    }

    private fun onSearch() {
        if (
            state.value.searchText.isEmpty()
            && state.value.selectedCourse == null
            && state.value.education.isBlank()
            && state.value.price.isBlank()
            && state.value.rating == 0
        ) {
            _state.value = state.value.copy(
                mentors = emptyList()
            )

            return
        }

        _state.value = state.value.copy(
            mentors = searchMentor.execute(
                name = state.value.searchText,
                course = state.value.selectedCourse,
                rating = state.value.rating,
                price = state.value.price,
                education = state.value.education
            )
        )
    }
}