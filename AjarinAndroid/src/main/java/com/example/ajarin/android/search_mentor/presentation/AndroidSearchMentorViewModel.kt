package com.example.ajarin.android.search_mentor.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.ajarin.android.core.domain.use_cases.AndroidSearchMentors
import com.example.ajarin.android.home.presentation.AndroidSearchMentorEvent
import com.example.ajarin.domain.core.model.allCourses
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.mentor.model.dummyMentors
import com.example.ajarin.domain.mentor.use_cases.SearchMentor
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.presentation.searchMentor.SearchMentorEvent
import com.example.ajarin.presentation.searchMentor.SearchMentorViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AndroidSearchMentorViewModel @Inject constructor(
    private val searchMentor: SearchMentor,
    private val androidSearchMentor: AndroidSearchMentors,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _searchMentors: MutableStateFlow<PagingData<Mentor>> = MutableStateFlow(PagingData.empty())
    val searchMentors: StateFlow<PagingData<Mentor>>
        get() = _searchMentors.asStateFlow()

    private val viewModel by lazy {
        SearchMentorViewModel(
            coroutineScope = viewModelScope
        )
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state = viewModel.state

    private var searchJob: Job? = null

    init {
        savedStateHandle.get<String>("courseId")?.let { courseId ->
            viewModelScope.launch {
                val course = allCourses.firstOrNull { it.id == courseId }

                delay(1000)

                course?.let {
                    onEvent(
                        event = SearchMentorEvent.OnPickCourse(
                            course = course
                        )
                    )

                    onEvent(
                        SearchMentorEvent.OnApply
                    )
                }
            }
        }
    }

    fun onEvent(event: SearchMentorEvent) {
        viewModel.onEvent(event)

        if (event == SearchMentorEvent.OnApply) {
            onEvent(AndroidSearchMentorEvent.OnSearch)
        }
    }

    fun onEvent(event: AndroidSearchMentorEvent) {
        when (event) {
            AndroidSearchMentorEvent.OnSearch -> {
                if (
                    state.value.searchText.isEmpty()
                    && state.value.selectedCourse == null
                    && state.value.education.isBlank()
                    && state.value.price.isBlank()
                    && state.value.rating == 0
                ) {
                    _searchMentors.value = PagingData.empty()

                    return
                }

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
//                    androidSearchMentor(
//                        name = state.value.searchText,
//                        education = state.value.education,
//                        rating = state.value.rating.toDouble(),
//                        courseId = state.value.selectedCourse?.id ?: "",
//                        price = state.value.price
//                    )
//                        .distinctUntilChanged()
//                        .cachedIn(viewModelScope)
//                        .collect {
//                            _searchMentors.value = it
//                        }

                    _searchMentors.value = PagingData.from(
                        dummyMentors
                            .asSequence()
                            .filter {
                                it.name.lowercase().contains(state.value.searchText.lowercase())
                                        && (state.value.selectedCourse?.let { course ->  it.courses.contains(course) } ?: true)
                                        && (if (state.value.rating!=0) (it.rating.toDoubleOrNull() ?: 0.0) > state.value.rating else true)
                                        && (if (state.value.price!="") it.priceCategory == state.value.price else true)
                                        && (if (state.value.education!="") it.education == state.value.education else true)
                            }
                            .toList()
                    )
                }
            }
        }
    }
}