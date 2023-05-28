package com.example.ajarin.android.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.core.utils.UiEvent
import com.example.ajarin.home.domain.use_cases.SearchMentorByCourse
import com.example.ajarin.home.presentation.HomeEvent
import com.example.ajarin.home.presentation.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AndroidHomeViewModel @Inject constructor(
    private val searchMentorByCourse: SearchMentorByCourse
): ViewModel() {
    private val viewModel by lazy {
        HomeViewModel(
            searchMentorByCourse = searchMentorByCourse,
            coroutineScope = viewModelScope
        )
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state = viewModel.state

    fun onEvent(event: HomeEvent) {
        viewModel.onEvent(event)
    }
}