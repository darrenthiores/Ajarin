package com.example.ajarin.android.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.android.core.domain.use_cases.AndroidGetMentors
import com.example.ajarin.android.core.domain.use_cases.AndroidSearchMentors
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.mentor.use_cases.GetMentors
import com.example.ajarin.domain.mentor.use_cases.SearchMentor
import com.example.ajarin.domain.message.use_cases.GetUnreadCount
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.presentation.home.HomeEvent
import com.example.ajarin.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AndroidHomeViewModel @Inject constructor(
    private val searchMentor: SearchMentor,
    private val androidSearchMentor: AndroidSearchMentors,
    private val getMentors: GetMentors,
    private val androidGetMentors: AndroidGetMentors,
    private val getUnreadCount: GetUnreadCount,
    private val preferences: Preferences
): ViewModel() {
    private val _mentors: MutableStateFlow<PagingData<Mentor>> = MutableStateFlow(PagingData.empty())
    val mentors: StateFlow<PagingData<Mentor>>
        get() = _mentors.asStateFlow()

    private val _searchMentors: MutableStateFlow<PagingData<Mentor>> = MutableStateFlow(PagingData.empty())
    val searchMentors: StateFlow<PagingData<Mentor>>
        get() = _searchMentors.asStateFlow()

    private val viewModel by lazy {
        HomeViewModel(
            getUnreadCount = getUnreadCount,
            coroutineScope = viewModelScope
        )
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state = viewModel.state

    private var searchMentorJob: Job? = null

    fun onEvent(event: HomeEvent) {
        viewModel.onEvent(event)

        if (event is HomeEvent.OnSelectCourse) {
            searchMentorJob?.cancel()
            searchMentorJob = viewModelScope.launch {
                androidSearchMentor(
                    name = "",
                    education = "",
                    rating = 0.0,
                    courseId = event.course.id,
                    price = ""
                )
                    .distinctUntilChanged()
                    .cachedIn(viewModelScope)
                    .collect {
                        _searchMentors.value = it
                    }
            }
        }
    }

    init {
        viewModel.initUnreadCount(
            userId = "U1"
        )

        viewModelScope.launch {
            androidGetMentors()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _mentors.value = it
                }
        }
    }
}